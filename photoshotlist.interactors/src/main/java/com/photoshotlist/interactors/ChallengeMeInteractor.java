package com.photoshotlist.interactors;

import com.photoshotlist.boundaries.input.CategoryResponseModel;
import com.photoshotlist.boundaries.input.ChallengeMeResponseModel;
import com.photoshotlist.boundaries.input.CompositionResponseModel;
import com.photoshotlist.boundaries.input.IChallengeMeInputBoundary;
import com.photoshotlist.boundaries.input.factories.CategoryResponseModelFactory;
import com.photoshotlist.boundaries.input.factories.ChallengeMeResponseModelFactory;
import com.photoshotlist.boundaries.input.factories.CompositionResponseModelFactory;
import com.photoshotlist.domainmodels.entities.Category;
import com.photoshotlist.domainmodels.entities.ChallengeMe;
import com.photoshotlist.domainmodels.entities.Composition;
import com.photoshotlist.domainservices.factories.ChallengeMeFactory;
import com.photoshotlist.domainservices.repositories.ICategoryRepository;
import com.photoshotlist.domainservices.repositories.ICompositionRepository;
import com.photoshotlist.domainservices.repositories.IImageRepository;
import com.photoshotlist.helpers.ImageHelper;

/**
 * Created by PhpDev on 2017/01/14.
 */

public class ChallengeMeInteractor implements IChallengeMeInteractor, IChallengeMeInputBoundary {

    private ICompositionRepository compositionRepository;
    private ICategoryRepository categoryRepository;
    private IImageRepository imageRepository;

    public ChallengeMeInteractor(ICompositionRepository compositionRepository,
                                 ICategoryRepository categoryRepository,
                                 IImageRepository imageRepository){
        this.compositionRepository = compositionRepository;
        this.categoryRepository = categoryRepository;
        this.imageRepository = imageRepository;
    }

    private Composition GetRandomComposition() {
        Composition composition = null;
        composition = this.compositionRepository.RandomComposition();

        if(composition == null)
            return composition;

        composition.setImages(this.imageRepository.GetByComposition(composition.getId()));

        return composition;
    }

    private Category GetRandomCategory() {
        Category category = null;
        category = this.categoryRepository.RandomCategory();

        if(category == null)
            return category;

        category.setImages(this.imageRepository.GetByCategory(category.getId()));

        return category;
    }

    @Override
    public ChallengeMe GetRandom() {

        ChallengeMe challengeMe = ChallengeMeFactory.getInstance().create(GetRandomCategory(),
                GetRandomComposition());

        return challengeMe;
    }

    @Override
    public ChallengeMeResponseModel Random() {

        ChallengeMe challengeMe = GetRandom();

        CategoryResponseModel categoryResponseModel = CategoryResponseModelFactory.getInstance()
                .create(challengeMe.getCategory().getId(),
                        challengeMe.getCategory().getName(),
                        challengeMe.getCategory().getLongDescription(),
                        challengeMe.getCategory().getImageResourceId(),
                        challengeMe.getCategory().isActive(),
                        ImageHelper.LoadImages(challengeMe.getCategory().getImages()));

        CompositionResponseModel compositionResponseModel = CompositionResponseModelFactory.getInstance()
                .create(challengeMe.getComposition().getId(),
                        challengeMe.getComposition().getName(),
                        challengeMe.getComposition().getLongDescription(),
                        challengeMe.getComposition().getImageResourceId(),
                        challengeMe.getComposition().isActive(),
                        ImageHelper.LoadImages(challengeMe.getComposition().getImages()));

        ChallengeMeResponseModel responseModel = ChallengeMeResponseModelFactory.getInstance().
                create(categoryResponseModel, compositionResponseModel);

        return responseModel;

    }
}