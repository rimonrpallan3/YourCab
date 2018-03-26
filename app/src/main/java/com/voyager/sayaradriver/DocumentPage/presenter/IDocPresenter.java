package com.voyager.sayaradriver.DocumentPage.presenter;

import com.voyager.sayaradriver.DocumentPage.view.IDocView;

/**
 * Created by rimon on 16/9/17.
 */

public class IDocPresenter implements IDocControler {

    IDocView iDocView;

    public IDocPresenter(IDocView iDocView) {
        this.iDocView = iDocView;
    }

    @Override
    public void validationCheck() {

    }
}
