package com.talfinder.assessment;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class UniqueEmailAddressTests {

  @Test
  public void ordinaryEmails() {
    UniqueEmailAddress uniqueEmailAddress = new UniqueEmailAddress();
    int numUniqueEmails = uniqueEmailAddress.numUniqueEmails(new String[]{"johnsmith@gmail.com"});
    assertThat(numUniqueEmails, is(equalTo(1)));

    // Invalid email addresses should not be counted
    numUniqueEmails = uniqueEmailAddress.numUniqueEmails(new String[]{"","johnsmith"});
    assertThat(numUniqueEmails, is(equalTo(0)));

    // verify that duplicates are accounted for
    numUniqueEmails = uniqueEmailAddress.numUniqueEmails(new String[]{"alice@gmail.com","bob@gmail.com","charlie@gmail.com","alice@gmail.com"});
    assertThat(numUniqueEmails, is(equalTo(3)));
  }

  @Test
  public void emailsWithDot() {
    UniqueEmailAddress uniqueEmailAddress = new UniqueEmailAddress();
    // Test for emails with no dot, single dot and multiple dots, all being the same
    int numUniqueEmails = uniqueEmailAddress.numUniqueEmails(new String[]{"johnsmith@gmail.com","john.smith@gmail.com","j.o.h.n.s.m.i.t.h@gmail.com"});
    assertThat(numUniqueEmails, is(equalTo(1)));

    // Verify the above test for multiple email addresses
    numUniqueEmails = uniqueEmailAddress.numUniqueEmails(new String[]{"janedoe@gmail.com","jane.doe@gmail.com", "jane.d.o.e@gmail.com","bob.smith@gmail.com","b.o.b.smith@gmail.com","bobsmith@gmail.com"});
    assertThat(numUniqueEmails, is(equalTo(2)));

    // Verify for that a set of unique email addresses containing dots is unique
    numUniqueEmails = uniqueEmailAddress.numUniqueEmails(new String[]{"john.smith@gmail.com","jane.doe@gmail.com"});
    assertThat(numUniqueEmails, is(equalTo(2)));
  }

  @Test
  public void emailsWithPlus() {
    UniqueEmailAddress uniqueEmailAddress = new UniqueEmailAddress();
    // Test for emails with no plus and single plus, all being the same
    int numUniqueEmails = uniqueEmailAddress.numUniqueEmails(new String[]{"johnsmith@gmail.com","johnsmith+news@gmail.com","johnsmith+spam@gmail.com"});
    assertThat(numUniqueEmails, is(equalTo(1)));

    // Test for emails with multiple plus, all being the same
    numUniqueEmails = uniqueEmailAddress.numUniqueEmails(new String[]{"johnsmith+important@gmail.com","johnsmith+important+info@gmail.com","johnsmith+breaking+news@gmail.com"});
    assertThat(numUniqueEmails, is(equalTo(1)));
  }

  @Test
  public void invalidEmails() {
    UniqueEmailAddress uniqueEmailAddress = new UniqueEmailAddress();
    // Test for addresses containing dot, but no domain
    int numUniqueEmails = uniqueEmailAddress.numUniqueEmails(new String[]{"johnsmith","john.smith","j.o.h.n.s.m.i.t.h"});
    assertThat(numUniqueEmails, is(equalTo(0)));

    // Test for emails with containing plus, but no domain
    numUniqueEmails = uniqueEmailAddress.numUniqueEmails(new String[]{"johnsmith","johnsmith+news","johnsmith+spam"});
    assertThat(numUniqueEmails, is(equalTo(0)));

    // Test for emails starting with dot, but no leading identifier
    numUniqueEmails = uniqueEmailAddress.numUniqueEmails(new String[]{".smith@gmail.com",".news@gmail.com"});
    assertThat(numUniqueEmails, is(equalTo(0)));

    // Test for emails starting with plus, but no leading identifier
    numUniqueEmails = uniqueEmailAddress.numUniqueEmails(new String[]{"+news@gmail.com","+spam@gmail.com"});
    assertThat(numUniqueEmails, is(equalTo(0)));

    // Test for emails ending with plus, which is actually valid
    numUniqueEmails = uniqueEmailAddress.numUniqueEmails(new String[]{"johnsmith+@gmail.com","janedoe+@gmail.com"});
    assertThat(numUniqueEmails, is(equalTo(2)));
  }
}
