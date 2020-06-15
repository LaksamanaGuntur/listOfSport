package co.id.listofleague.model;

import co.id.listofleague.data.DaoSession;

/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

public class BaseModel {
    protected final DaoSession mDaoSession;

    public BaseModel(DaoSession daoSession) {
        mDaoSession = daoSession;
    }
}
