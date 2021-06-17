package com.example.travel.view;

import com.example.travel.base.IBaseCallback;
import com.example.travel.model.domain.SecondListCategoryResponse;
import com.example.travel.model.domain.SecondListResponse;

import java.util.List;

public interface ISecondListCallback extends IBaseCallback {


       void onTagListLoad (List<SecondListCategoryResponse> listResponses);
}
