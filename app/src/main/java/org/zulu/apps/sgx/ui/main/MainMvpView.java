package org.zulu.apps.sgx.ui.main;

import java.util.List;

import org.zulu.apps.sgx.data.model.Ribot;
import org.zulu.apps.sgx.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showRibots(List<Ribot> ribots);

    void showRibotsEmpty();

    void showError();

}
