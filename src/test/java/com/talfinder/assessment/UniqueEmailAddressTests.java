package com.talfinder.assessment;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class UniqueEmailAddressTests {

  private String[] emails;
  private int expected;

  public UniqueEmailAddressTests(String[] emails, int expected) {
    this.emails = emails;
    this.expected = expected;
  }

  @Parameters
  public static Collection<Object[]> parameters() {
    return Arrays.asList(new Object[][] {
        {new String[]{"johnsmith@gmail.com"}, 1},
        {new String[]{"alice@gmail.com","bob@gmail.com","charlie@gmail.com","alice@gmail.com"}, 3},
        {new String[]{"johnsmith@gmail.com","john.smith@gmail.com","j.o.h.n.s.m.i.t.h@gmail.com"}, 1},
        {new String[]{"janedoe@gmail.com","jane.doe@gmail.com", "jane.d.o.e@gmail.com","bob.smith@gmail.com","b.o.b.smith@gmail.com","bobsmith@gmail.com"}, 2},
        {new String[]{"john.smith@gmail.com","jane.doe@gmail.com"}, 2},
        {new String[]{"johnsmith@gmail.com","johnsmith+news@gmail.com","johnsmith+spam@gmail.com"}, 1},
        {new String[]{"johnsmith+important@gmail.com","johnsmith+important+info@gmail.com","johnsmith+breaking+news@gmail.com"}, 1},
        {new String[]{"","johnsmith"}, 0},
        {new String[]{"johnsmith","john.smith","j.o.h.n.s.m.i.t.h"}, 0},
        {new String[]{"\"johnsmith\",\"johnsmith+news\",\"johnsmith+spam\""}, 0},
        {new String[]{".smith@gmail.com",".news@gmail.com"}, 0},
        {new String[]{"+news@gmail.com","+spam@gmail.com"}, 0}
    });
  }

  @Test
  public void checkForUniqueEmails() {
    Assert.assertEquals("input:Email List " + Arrays.toString(emails), expected, UniqueEmailAddress.numUniqueEmails(emails));
  }
}
