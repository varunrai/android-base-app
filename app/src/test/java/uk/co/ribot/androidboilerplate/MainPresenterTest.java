package org.zulu.apps.alerto;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import rx.Observable;
import org.zulu.apps.alerto.data.DataManager;
import org.zulu.apps.alerto.data.model.Ribot;
import org.zulu.apps.alerto.test.common.TestDataFactory;
import org.zulu.apps.alerto.ui.main.MainMvpView;
import org.zulu.apps.alerto.ui.main.MainPresenter;
import org.zulu.apps.alerto.util.RxSchedulersOverrideRule;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock MainMvpView mMockMainMvpView;
    @Mock DataManager mMockDataManager;
    private MainPresenter mMainPresenter;

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() {
        mMainPresenter = new MainPresenter(mMockDataManager);
        mMainPresenter.attachView(mMockMainMvpView);
    }

    @After
    public void tearDown() {
        mMainPresenter.detachView();
    }

    @Test
    public void loadRibotsReturnsRibots() {
        List<Ribot> ribots = TestDataFactory.makeListRibots(10);
        doReturn(Observable.just(ribots))
                .when(mMockDataManager)
                .getRibots();

        mMainPresenter.loadRibots();
        verify(mMockMainMvpView).showRibots(ribots);
        verify(mMockMainMvpView, never()).showRibotsEmpty();
        verify(mMockMainMvpView, never()).showError();
    }

    @Test
    public void loadRibotsReturnsEmptyList() {
        doReturn(Observable.just(Collections.emptyList()))
                .when(mMockDataManager)
                .getRibots();

        mMainPresenter.loadRibots();
        verify(mMockMainMvpView).showRibotsEmpty();
        verify(mMockMainMvpView, never()).showRibots(anyListOf(Ribot.class));
        verify(mMockMainMvpView, never()).showError();
    }

    @Test
    public void loadRibotsFails() {
        doReturn(Observable.error(new RuntimeException()))
                .when(mMockDataManager)
                .getRibots();

        mMainPresenter.loadRibots();
        verify(mMockMainMvpView).showError();
        verify(mMockMainMvpView, never()).showRibotsEmpty();
        verify(mMockMainMvpView, never()).showRibots(anyListOf(Ribot.class));
    }
}
