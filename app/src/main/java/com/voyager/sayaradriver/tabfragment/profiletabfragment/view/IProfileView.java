package com.voyager.sayaradriver.tabfragment.profiletabfragment.view;

import com.voyager.sayaradriver.tabfragment.profiletabfragment.model.Documents;
import com.voyager.sayaradriver.tabfragment.profiletabfragment.model.ProfileModel;

import java.util.List;

/**
 * Created by User on 09-Feb-18.
 */

public interface IProfileView {
    void loadData(List<ProfileModel> profileModel);
}
