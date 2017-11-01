package com.photoshotlist.domainservices.factories;

import com.photoshotlist.domainmodels.entities.Category;
import com.photoshotlist.domainmodels.entities.ChallengeMe;
import com.photoshotlist.domainmodels.entities.Composition;

/**
 * Created by PhpDev on 2016/12/04.
 */
public class ChallengeMeFactory implements IChallengeMeFactory {

    private static ChallengeMeFactory instance;

    private ChallengeMeFactory() { }

    public static ChallengeMeFactory getInstance()
    {
        if (instance == null) {
            instance = new ChallengeMeFactory();
        }
        return instance;
    }

    @Override
    public ChallengeMe create(Category category, Composition composition) {

        ChallengeMe challengeMe = new ChallengeMe();

        if(category == null)
            return null;

        challengeMe.setCategory(category);

        if(composition == null)
            return null;

        challengeMe.setComposition(composition);

        return challengeMe;
    }
}
