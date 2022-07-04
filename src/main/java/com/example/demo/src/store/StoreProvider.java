package com.example.demo.src.store;

import com.example.demo.config.BaseException;
import com.example.demo.src.store.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service

public class StoreProvider {

    private final StoreDao storeDao;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public StoreProvider(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    public List<GetStoreRes> getStore() throws BaseException{
        try{
            List<GetStoreRes> getStoreRes = storeDao.getStore();
            return getStoreRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

//    public List<GetstoreRes> getstore(int storeIdx) throws BaseException{
//        if (checkstoreIdx(storeIdx) == 0) {
//            throw new BaseException(GET_MOVIES_NONEXISTENCE_store);
//        }
//        try{
//            List<GetstoreRes> getstoreRes = storeDao.getstore(storeIdx);
//            return getstoreRes;
//        }
//        catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    public int checkstoreIdx(int storeIdx) throws BaseException{
//        try{
//            return storeDao.checkstoreIdx(storeIdx);
//        } catch (Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }


}
