package com.photoshotlist.domainservices.factories;

import com.photoshotlist.domainmodels.entities.Category;
import com.photoshotlist.domainmodels.entities.ChallengeMe;
import com.photoshotlist.domainmodels.entities.Composition;

/**
 * Created by PhpDev on 2016/12/04.
 */
public interface IChallengeMeFactory {
    public ChallengeMe create(Category category, Composition composition);
}