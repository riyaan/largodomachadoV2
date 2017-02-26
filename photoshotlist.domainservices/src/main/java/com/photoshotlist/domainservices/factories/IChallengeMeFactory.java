package com.photoshotlist.domainservices.factories;

import com.photoshotlist.domainmodels.entities.Category;
import com.photoshotlist.domainmodels.entities.ChallengeMe;
import com.photoshotlist.domainmodels.entities.Composition;
import com.photoshotlist.domainmodels.entities.Image;

import java.util.List;

/**
 * Created by PhpDev on 2016/12/04.
 */
public interface IChallengeMeFactory {
    public ChallengeMe create(Category category, Composition composition);
}