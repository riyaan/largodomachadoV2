package com.photoshotlist.domainmodels.entities;

/**
 * Created by PhpDev on 2016/08/21.
 */
public class ChallengeMe {
    private Category category;
    private Composition composition;

    public ChallengeMe() {}

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Composition getComposition() {
        return composition;
    }

    public void setComposition(Composition composition) {
        this.composition = composition;
    }
}