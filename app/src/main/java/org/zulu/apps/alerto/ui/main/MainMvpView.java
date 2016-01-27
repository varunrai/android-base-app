package org.zulu.apps.alerto.ui.main;

import java.util.List;

import org.zulu.apps.alerto.data.model.Ribot;
import org.zulu.apps.alerto.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showRibots(List<Ribot> ribots);

    void showRibotsEmpty();

    void showError();

}
