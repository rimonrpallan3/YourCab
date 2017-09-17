package com.voyager.user.yourcab.DocumentPage.presenter;

import com.voyager.user.yourcab.DocumentPage.view.IDocView;

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
