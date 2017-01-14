package com.photoshotlist.testsuite;

import com.photoshotlist.boundaries.input.InputBoundaryTest;
import com.photoshotlist.interactors.CategoryInteractorTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        InputBoundaryTest.class,
        CategoryInteractorTest.class})
public class AllTests {

}