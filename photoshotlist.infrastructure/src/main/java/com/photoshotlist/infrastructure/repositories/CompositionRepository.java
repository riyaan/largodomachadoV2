package com.photoshotlist.infrastructure.repositories;

import android.content.Context;

import com.photoshotlist.domainmodels.entities.Composition;
import com.photoshotlist.domainmodels.entities.Image;
import com.photoshotlist.domainservices.factories.CompositionFactory;
import com.photoshotlist.domainservices.repositories.ICompositionRepository;
import com.photoshotlist.infrastructure.helper.CompositionDAO;
import com.photoshotlist.infrastructure.helper.PSLDatabaseHelper;
import com.photoshotlist.infrastructure.helper.PSLDatabaseHelperFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PhpDev on 2016/12/04.
 */
public class CompositionRepository implements ICompositionRepository {

    private Context _context;

    public CompositionRepository(Context context){
        this._context = context;
    }

    @Override
    /**
     * Retrieve a Composition Rule from the Persistence mechanism
     @return A Composition rule or NULL object when the composition rule does not exist
     @exception  Exception
     */
    public Composition GetById(int id) {

        PSLDatabaseHelper databaseHelper = PSLDatabaseHelperFactory.getInstance().create(_context);

        // TODO: Create a factory method
        CompositionDAO compositionDAO = null;
        Composition composition = null;

        try {
            compositionDAO = databaseHelper.GetCompositionById(id);
        }catch (Exception ex) {
        }

        if(compositionDAO == null)
            return  composition;

        composition = CompositionFactory.getInstance().create(compositionDAO.getId(), compositionDAO.getName(),
                compositionDAO.getLongDescription(), compositionDAO.getImageResourceId(),
                compositionDAO.isActive(), new ArrayList<Image>());

        return composition;
    }

    @Override
    /**
     * Retrieve all Compositions from the Persistence mechanism
     @return A list of Compositions or an empty list when no Compositions exist
     @exception  Exception
     */
    public List<Composition> GetAll() {

        PSLDatabaseHelper databaseHelper = PSLDatabaseHelperFactory.getInstance().create(_context);

        // TODO: Create a factory method
        List<CompositionDAO> compositionDAOList = new ArrayList<CompositionDAO>();
        List<Composition> compositionList = new ArrayList<Composition>();

        try {
            compositionDAOList = databaseHelper.GetAllCompositions();
        }catch (Exception ex) {
            // TODO: Handle database exceptions
        }

        if(compositionDAOList.size() == 0)
            return  compositionList;

        for(CompositionDAO item : compositionDAOList) {

            Composition composition = CompositionFactory.getInstance().create(item.getId(), item.getName(),
                    item.getLongDescription(), item.getImageResourceId(),
                    item.isActive(), new ArrayList<Image>());

            compositionList.add(composition);
        }

        return compositionList;
    }

    @Override
    /**
     * Retrieve a Composition from the Persistence mechanism
     @return A Composition or NULL object when the composition does not exist
     @exception  Exception
     */
    public Composition GetByName(String name) {

        PSLDatabaseHelper databaseHelper = PSLDatabaseHelperFactory.getInstance().create(_context);

        // TODO: Create a factory method
        CompositionDAO compositionDAO = null;
        Composition composition = null;

        try {
            compositionDAO = databaseHelper.GetCompositionByName(name);
        }catch (Exception ex) {
        }

        if(compositionDAO == null)
            return  composition;

        composition = CompositionFactory.getInstance().create(compositionDAO.getId(), compositionDAO.getName(),
                compositionDAO.getLongDescription(), compositionDAO.getImageResourceId(),
                compositionDAO.isActive(), new ArrayList<Image>());

        return composition;
    }

    @Override
    public Composition RandomComposition() {
        PSLDatabaseHelper databaseHelper = PSLDatabaseHelperFactory.getInstance().create(_context);

        // TODO: Create a factory method
        CompositionDAO compositionDAO = null;
        Composition composition = null;

        try {
            compositionDAO = databaseHelper.RandomComposition();
        }catch (Exception ex) {
        }

        if(compositionDAO == null)
            return  composition;

        composition = CompositionFactory.getInstance().create(compositionDAO.getId(), compositionDAO.getName(),
                compositionDAO.getLongDescription(), compositionDAO.getImageResourceId(),
                compositionDAO.isActive(), new ArrayList<Image>());

        return composition;
    }
}