package com.photoshotlist.domainmodels.entities;

import org.junit.Assert;
import org.junit.Test;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class DomainModelCompositionUnitTest {
    @Test
    public void getCompositionName_Success() throws Exception {

        final String COMPOSITION_NAME = "Rule of Thirds";

        Composition composition = new Composition();
        composition.setName(COMPOSITION_NAME);

        String actual = composition.getName();

        Assert.assertEquals(COMPOSITION_NAME, actual);
    }

    @Test
    public void getCompositionName_Failure() throws Exception {

        final String COMPOSITION_NAME              = "Rule of Thirds";
        final String INCORRECT_COMPOSITION_NAME    = "Invalid";

        Composition composition = new Composition();
        composition.setName(COMPOSITION_NAME);

        String actual = composition.getName();

        Assert.assertNotEquals(INCORRECT_COMPOSITION_NAME, actual);
    }
}
