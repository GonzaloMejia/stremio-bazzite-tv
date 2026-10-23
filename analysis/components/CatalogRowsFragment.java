package com.stremio.tv.views.metarow;

import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.leanback.app.RowsSupportFragment;
import androidx.leanback.widget.BaseOnItemViewClickedListener;
import androidx.leanback.widget.BaseOnItemViewSelectedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.RowPresenter;
import androidx.lifecycle.FlowExtKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.NavController;
import androidx.navigation.fragment.FragmentKt;
import com.stremio.common.extensions.ContextExtKt;
import com.stremio.common.utils.DeeplinkUtil;
import com.stremio.common.viewmodels.CatalogRowsViewModel;
import com.stremio.common.viewmodels.MetaPreviewViewModel;
import com.stremio.common.viewmodels.PreferencesViewModel;
import com.stremio.common.viewmodels.state.MetaPreviewEvent;
import com.stremio.common.viewmodels.state.MetaRow;
import com.stremio.common.viewmodels.state.RowsEvent;
import com.stremio.core.Field;
import com.stremio.core.types.library.LibraryItem;
import com.stremio.core.types.resource.MetaItemDeepLinks;
import com.stremio.core.types.resource.MetaItemPreview;
import com.stremio.tv.R;
import com.stremio.tv.views.metarow.model.ContinueWatchingItemModel;
import com.stremio.tv.views.metarow.model.LibraryItemModel;
import com.stremio.tv.views.metarow.model.MetaItemModel;
import java.util.List;
import jp.co.cyberagent.lounge.ListRowModel;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.viewmodel.GetViewModelKt;

/* JADX INFO: compiled from: CatalogRowsFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u0000 32\u00020\u0001:\u00013B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u001a\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020!2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0006\u0010\"\u001a\u00020\u001cJ\"\u0010#\u001a\u00020\u001c2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020!2\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\u0010\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0016J\u001c\u0010-\u001a\u00020\u001c2\b\u0010.\u001a\u0004\u0018\u00010/2\b\b\u0002\u00100\u001a\u00020*H\u0002J\u0014\u00101\u001a\u0004\u0018\u00010\u001a2\b\u00102\u001a\u0004\u0018\u00010\u001aH\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\f\u0010\rR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\t\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\t\u001a\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000Ê\u0001\f\b5\u0012\b\b6\u0012\u0004\b\u0003\u0010\u0000¨\u00064"}, d2 = {"Lcom/stremio/tv/views/metarow/CatalogRowsFragment;", "Landroidx/leanback/app/RowsSupportFragment;", "<init>", "()V", "rowsViewModel", "Lcom/stremio/common/viewmodels/CatalogRowsViewModel;", "getRowsViewModel", "()Lcom/stremio/common/viewmodels/CatalogRowsViewModel;", "rowsViewModel$delegate", "Lkotlin/Lazy;", "previewViewModel", "Lcom/stremio/common/viewmodels/MetaPreviewViewModel;", "getPreviewViewModel", "()Lcom/stremio/common/viewmodels/MetaPreviewViewModel;", "previewViewModel$delegate", "preferencesViewModel", "Lcom/stremio/common/viewmodels/PreferencesViewModel;", "getPreferencesViewModel", "()Lcom/stremio/common/viewmodels/PreferencesViewModel;", "preferencesViewModel$delegate", "controller", "Lcom/stremio/tv/views/metarow/CatalogRowsController;", "getController", "()Lcom/stremio/tv/views/metarow/CatalogRowsController;", "controller$delegate", "contextModel", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "Landroid/view/View;", "setupListeners", "onCreateContextMenu", "menu", "Landroid/view/ContextMenu;", "v", "menuInfo", "Landroid/view/ContextMenu$ContextMenuInfo;", "onContextItemSelected", "", "menuItem", "Landroid/view/MenuItem;", "navigate", "deeplink", "", "autoPlay", "fromModel", "itemModel", "Companion", "Stremio:androidTV-com.stremio.one-1.11.2-40000002_release", "Landroidx/compose/runtime/internal/StabilityInferred;", "parameters"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class CatalogRowsFragment extends RowsSupportFragment {
    private static final int ADD_TO_LIB_ID = 4;
    private static final int CLEAR_PROGRESS_ID = 2;
    private static final int DETAILS_ID = 1;
    private static final int DISMISS_NOTIFICATIONS_ID = 3;
    private static final int MARK_AS_UNWATCHED = 7;
    private static final int MARK_AS_WATCHED = 6;
    private static final int REMOVE_FROM_LIB_ID = 5;
    private Object contextModel;
    public static final int $stable = 8;

    /* JADX INFO: renamed from: rowsViewModel$delegate, reason: from kotlin metadata */
    private final Lazy rowsViewModel = LazyKt.lazy(new Function0() { // from class: com.stremio.tv.views.metarow.CatalogRowsFragment$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return CatalogRowsFragment.rowsViewModel_delegate$lambda$0(this.f$0);
        }
    });

    /* JADX INFO: renamed from: previewViewModel$delegate, reason: from kotlin metadata */
    private final Lazy previewViewModel = LazyKt.lazy(new Function0() { // from class: com.stremio.tv.views.metarow.CatalogRowsFragment$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return CatalogRowsFragment.previewViewModel_delegate$lambda$0(this.f$0);
        }
    });

    /* JADX INFO: renamed from: preferencesViewModel$delegate, reason: from kotlin metadata */
    private final Lazy preferencesViewModel = LazyKt.lazy(new Function0() { // from class: com.stremio.tv.views.metarow.CatalogRowsFragment$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return CatalogRowsFragment.preferencesViewModel_delegate$lambda$0(this.f$0);
        }
    });

    /* JADX INFO: renamed from: controller$delegate, reason: from kotlin metadata */
    private final Lazy controller = LazyKt.lazy(new Function0() { // from class: com.stremio.tv.views.metarow.CatalogRowsFragment$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return CatalogRowsFragment.controller_delegate$lambda$0(this.f$0);
        }
    });

    private final CatalogRowsViewModel getRowsViewModel() {
        return (CatalogRowsViewModel) this.rowsViewModel.getValue();
    }

    static final CatalogRowsViewModel rowsViewModel_delegate$lambda$0(CatalogRowsFragment catalogRowsFragment) {
        final Fragment fragmentRequireParentFragment = catalogRowsFragment.requireParentFragment();
        Intrinsics.checkNotNullExpressionValue(fragmentRequireParentFragment, "requireParentFragment(...)");
        ViewModelStore viewModelStore = new Function0<ViewModelStoreOwner>() { // from class: com.stremio.tv.views.metarow.CatalogRowsFragment$rowsViewModel_delegate$lambda$0$$inlined$getViewModel$default$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return fragmentRequireParentFragment;
            }
        }.invoke().getViewModelStore();
        CreationExtras defaultViewModelCreationExtras = fragmentRequireParentFragment.getDefaultViewModelCreationExtras();
        Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "<get-defaultViewModelCreationExtras>(...)");
        return (CatalogRowsViewModel) GetViewModelKt.resolveViewModel$default(Reflection.getOrCreateKotlinClass(CatalogRowsViewModel.class), viewModelStore, null, defaultViewModelCreationExtras, null, AndroidKoinScopeExtKt.getKoinScope(fragmentRequireParentFragment), null, 4, null);
    }

    private final MetaPreviewViewModel getPreviewViewModel() {
        return (MetaPreviewViewModel) this.previewViewModel.getValue();
    }

    static final MetaPreviewViewModel previewViewModel_delegate$lambda$0(CatalogRowsFragment catalogRowsFragment) {
        final Fragment fragmentRequireParentFragment = catalogRowsFragment.requireParentFragment();
        Intrinsics.checkNotNullExpressionValue(fragmentRequireParentFragment, "requireParentFragment(...)");
        ViewModelStore viewModelStore = new Function0<ViewModelStoreOwner>() { // from class: com.stremio.tv.views.metarow.CatalogRowsFragment$previewViewModel_delegate$lambda$0$$inlined$getViewModel$default$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return fragmentRequireParentFragment;
            }
        }.invoke().getViewModelStore();
        CreationExtras defaultViewModelCreationExtras = fragmentRequireParentFragment.getDefaultViewModelCreationExtras();
        Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "<get-defaultViewModelCreationExtras>(...)");
        return (MetaPreviewViewModel) GetViewModelKt.resolveViewModel$default(Reflection.getOrCreateKotlinClass(MetaPreviewViewModel.class), viewModelStore, null, defaultViewModelCreationExtras, null, AndroidKoinScopeExtKt.getKoinScope(fragmentRequireParentFragment), null, 4, null);
    }

    private final PreferencesViewModel getPreferencesViewModel() {
        return (PreferencesViewModel) this.preferencesViewModel.getValue();
    }

    static final PreferencesViewModel preferencesViewModel_delegate$lambda$0(CatalogRowsFragment catalogRowsFragment) {
        final Fragment fragmentRequireParentFragment = catalogRowsFragment.requireParentFragment();
        Intrinsics.checkNotNullExpressionValue(fragmentRequireParentFragment, "requireParentFragment(...)");
        ViewModelStore viewModelStore = new Function0<ViewModelStoreOwner>() { // from class: com.stremio.tv.views.metarow.CatalogRowsFragment$preferencesViewModel_delegate$lambda$0$$inlined$getViewModel$default$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return fragmentRequireParentFragment;
            }
        }.invoke().getViewModelStore();
        CreationExtras defaultViewModelCreationExtras = fragmentRequireParentFragment.getDefaultViewModelCreationExtras();
        Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "<get-defaultViewModelCreationExtras>(...)");
        return (PreferencesViewModel) GetViewModelKt.resolveViewModel$default(Reflection.getOrCreateKotlinClass(PreferencesViewModel.class), viewModelStore, null, defaultViewModelCreationExtras, null, AndroidKoinScopeExtKt.getKoinScope(fragmentRequireParentFragment), null, 4, null);
    }

    static final CatalogRowsController controller_delegate$lambda$0(CatalogRowsFragment catalogRowsFragment) {
        Lifecycle lifecycle = catalogRowsFragment.getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "<get-lifecycle>(...)");
        return new CatalogRowsController(lifecycle, catalogRowsFragment.getPreferencesViewModel());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CatalogRowsController getController() {
        return (CatalogRowsController) this.controller.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAdapter(getController().getAdapter());
    }

    @Override // androidx.leanback.app.RowsSupportFragment, androidx.leanback.app.BaseRowSupportFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        setupListeners();
        StateFlow<List<MetaRow>> state = getRowsViewModel().getState();
        Lifecycle lifecycle = getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "<get-lifecycle>(...)");
        FlowKt.launchIn(FlowKt.onEach(FlowExtKt.flowWithLifecycle(state, lifecycle, Lifecycle.State.STARTED), new AnonymousClass1(null)), LifecycleOwnerKt.getLifecycleScope(this));
    }

    /* JADX INFO: renamed from: com.stremio.tv.views.metarow.CatalogRowsFragment$onViewCreated$1, reason: invalid class name */
    /* JADX INFO: compiled from: CatalogRowsFragment.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n"}, d2 = {"<anonymous>", "", "it", "", "Lcom/stremio/common/viewmodels/state/MetaRow;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.tv.views.metarow.CatalogRowsFragment$onViewCreated$1", f = "CatalogRowsFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<List<? extends MetaRow>, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = CatalogRowsFragment.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(List<? extends MetaRow> list, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(list, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            List<? extends MetaRow> list = (List) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CatalogRowsFragment.this.getController().setRows(list);
            return Unit.INSTANCE;
        }
    }

    public final void setupListeners() {
        setOnItemViewClickedListener(new BaseOnItemViewClickedListener() { // from class: com.stremio.tv.views.metarow.CatalogRowsFragment$$ExternalSyntheticLambda4
            @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
            public final void onItemClicked(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Object obj2) {
                CatalogRowsFragment.setupListeners$lambda$0(this.f$0, viewHolder, obj, viewHolder2, obj2);
            }
        });
        setOnItemViewSelectedListener(new BaseOnItemViewSelectedListener() { // from class: com.stremio.tv.views.metarow.CatalogRowsFragment$$ExternalSyntheticLambda5
            @Override // androidx.leanback.widget.BaseOnItemViewSelectedListener
            public final void onItemSelected(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Object obj2) {
                CatalogRowsFragment.setupListeners$lambda$1(this.f$0, viewHolder, obj, viewHolder2, obj2);
            }
        });
    }

    static final void setupListeners$lambda$0(CatalogRowsFragment catalogRowsFragment, Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Object obj2) {
        MetaItemDeepLinks deepLinks;
        String metaDetailsStreams;
        Object objFromModel = catalogRowsFragment.fromModel(obj);
        if (objFromModel == null) {
            ContextExtKt.shortToast(catalogRowsFragment, R.string.label_stremio_tv_catalog_loading);
        }
        boolean z = false;
        int iSwitchDispatch$1 = CatalogRowsFragment$$ExternalSyntheticTypeSwitchClass6.switchDispatch$1(objFromModel, 0);
        String metaDetailsVideos = null;
        if (iSwitchDispatch$1 == 0) {
            deepLinks = ((LibraryItem) objFromModel).getDeepLinks();
        } else {
            deepLinks = iSwitchDispatch$1 != 1 ? null : ((MetaItemPreview) objFromModel).getDeepLinks();
        }
        if ((objFromModel instanceof LibraryItem) && ((LibraryItem) objFromModel).getState().getTimeOffset() > 0) {
            z = true;
        }
        if (deepLinks != null && (metaDetailsStreams = deepLinks.getMetaDetailsStreams()) != null) {
            metaDetailsVideos = metaDetailsStreams;
        } else if (deepLinks != null) {
            metaDetailsVideos = deepLinks.getMetaDetailsVideos();
        }
        if (metaDetailsVideos != null) {
            catalogRowsFragment.navigate(metaDetailsVideos, z);
        }
    }

    static final void setupListeners$lambda$1(CatalogRowsFragment catalogRowsFragment, Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Object obj2) {
        Object objFromModel = catalogRowsFragment.fromModel(obj);
        MetaRow metaRowMetaRowFromKey = null;
        ListRowModel listRowModel = obj2 instanceof ListRowModel ? (ListRowModel) obj2 : null;
        if (listRowModel != null) {
            metaRowMetaRowFromKey = catalogRowsFragment.getController().metaRowFromKey(listRowModel.getKey());
        }
        catalogRowsFragment.getPreviewViewModel().onEvent(new MetaPreviewEvent.PreviewMetaItem(objFromModel, (objFromModel instanceof LibraryItem) || Intrinsics.areEqual(catalogRowsFragment.getRowsViewModel().getField(), Field.SEARCH.INSTANCE)));
        catalogRowsFragment.getRowsViewModel().onEvent(new RowsEvent.ItemSelected(metaRowMetaRowFromKey, objFromModel));
        if (metaRowMetaRowFromKey != null) {
            catalogRowsFragment.getRowsViewModel().onEvent(new RowsEvent.LoadRow(metaRowMetaRowFromKey));
        }
        if (obj != null) {
            catalogRowsFragment.registerForContextMenu(viewHolder.view);
            catalogRowsFragment.contextModel = obj;
        }
    }

    /* JADX WARN: Code duplicated, block: B:35:0x0097  */
    @Override // androidx.fragment.app.Fragment, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(v, "v");
        super.onCreateContextMenu(menu, v, menuInfo);
        Object objFromModel = fromModel(this.contextModel);
        if (objFromModel != null) {
            menu.add(0, 1, 0, R.string.label_library_details);
        }
        boolean z = objFromModel instanceof LibraryItem;
        if (z && ((LibraryItem) objFromModel).getState().getTimeOffset() > 0) {
            menu.add(0, 2, 0, R.string.label_library_clear_progress);
        }
        if (z && ((LibraryItem) objFromModel).getNotifications() > 0) {
            menu.add(0, 3, 0, R.string.label_ctx_dismiss_notif);
        }
        boolean z2 = objFromModel instanceof MetaItemPreview;
        if (z2 && !((MetaItemPreview) objFromModel).getInLibrary()) {
            menu.add(0, 4, 0, R.string.label_add_to_lib);
        }
        if ((this.contextModel instanceof LibraryItemModel) || (z2 && ((MetaItemPreview) objFromModel).getInLibrary())) {
            menu.add(0, 5, 0, R.string.label_remove_from_lib);
        }
        if (!(this.contextModel instanceof ContinueWatchingItemModel)) {
            if (z && !((LibraryItem) objFromModel).getWatched()) {
                menu.add(0, 6, 0, R.string.label_mark_as_watched);
            } else if (z2) {
                MetaItemPreview metaItemPreview = (MetaItemPreview) objFromModel;
                if (!metaItemPreview.getWatched() && metaItemPreview.getInLibrary()) {
                    menu.add(0, 6, 0, R.string.label_mark_as_watched);
                }
            }
        }
        if (this.contextModel instanceof ContinueWatchingItemModel) {
            return;
        }
        if ((z && ((LibraryItem) objFromModel).getWatched()) || (z2 && ((MetaItemPreview) objFromModel).getWatched())) {
            menu.add(0, 7, 0, R.string.label_mark_as_unwatched);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // androidx.fragment.app.Fragment
    public boolean onContextItemSelected(MenuItem menuItem) {
        MetaItemDeepLinks deepLinks;
        String id;
        String id2;
        String id3;
        Intrinsics.checkNotNullParameter(menuItem, "menuItem");
        Object objFromModel = fromModel(this.contextModel);
        String id4 = null;
        String id5 = null;
        String id6 = null;
        switch (menuItem.getItemId()) {
            case 1:
                int iSwitchDispatch$1 = CatalogRowsFragment$$ExternalSyntheticTypeSwitchClass6.switchDispatch$1(objFromModel, 0);
                if (iSwitchDispatch$1 == 0) {
                    deepLinks = ((LibraryItem) objFromModel).getDeepLinks();
                } else {
                    deepLinks = iSwitchDispatch$1 != 1 ? null : ((MetaItemPreview) objFromModel).getDeepLinks();
                }
                if (deepLinks != null) {
                    String metaDetailsVideos = deepLinks.getMetaDetailsVideos();
                    if (metaDetailsVideos == null) {
                        metaDetailsVideos = deepLinks.getMetaDetailsStreams();
                    }
                    if (metaDetailsVideos != null) {
                        navigate$default(this, metaDetailsVideos, false, 2, null);
                    }
                }
                return true;
            case 2:
                if (objFromModel != null) {
                    LibraryItem libraryItem = objFromModel instanceof LibraryItem ? (LibraryItem) objFromModel : null;
                    if (libraryItem != null) {
                        getPreviewViewModel().onEvent(new MetaPreviewEvent.RewindLibraryItem(libraryItem));
                    }
                }
                return true;
            case 3:
                if (objFromModel != null) {
                    LibraryItem libraryItem2 = objFromModel instanceof LibraryItem ? (LibraryItem) objFromModel : null;
                    if (libraryItem2 != null) {
                        getPreviewViewModel().onEvent(new MetaPreviewEvent.DismissNotifications(libraryItem2));
                    }
                }
                return true;
            case 4:
                if (objFromModel != null) {
                    MetaItemPreview metaItemPreview = objFromModel instanceof MetaItemPreview ? (MetaItemPreview) objFromModel : null;
                    if (metaItemPreview != null) {
                        getPreviewViewModel().onEvent(new MetaPreviewEvent.AddToLibrary(metaItemPreview));
                    }
                }
                return true;
            case 5:
                if (objFromModel != null) {
                    MetaItemPreview metaItemPreview2 = objFromModel instanceof MetaItemPreview ? (MetaItemPreview) objFromModel : null;
                    if (metaItemPreview2 == null || (id = metaItemPreview2.getId()) == null) {
                        LibraryItem libraryItem3 = objFromModel instanceof LibraryItem ? (LibraryItem) objFromModel : null;
                        if (libraryItem3 != null) {
                            id6 = libraryItem3.getId();
                        }
                    } else {
                        id6 = id;
                    }
                    if (id6 != null) {
                        getPreviewViewModel().onEvent(new MetaPreviewEvent.RemoveFromLibrary(id6));
                    }
                }
                return true;
            case 6:
                if (objFromModel != null) {
                    MetaItemPreview metaItemPreview3 = objFromModel instanceof MetaItemPreview ? (MetaItemPreview) objFromModel : null;
                    if (metaItemPreview3 == null || (id2 = metaItemPreview3.getId()) == null) {
                        LibraryItem libraryItem4 = objFromModel instanceof LibraryItem ? (LibraryItem) objFromModel : null;
                        if (libraryItem4 != null) {
                            id5 = libraryItem4.getId();
                        }
                    } else {
                        id5 = id2;
                    }
                    if (id5 != null) {
                        getPreviewViewModel().onEvent(new MetaPreviewEvent.MarkAsWatched(id5, true));
                    }
                }
                return true;
            case 7:
                if (objFromModel != null) {
                    MetaItemPreview metaItemPreview4 = objFromModel instanceof MetaItemPreview ? (MetaItemPreview) objFromModel : null;
                    if (metaItemPreview4 == null || (id3 = metaItemPreview4.getId()) == null) {
                        LibraryItem libraryItem5 = objFromModel instanceof LibraryItem ? (LibraryItem) objFromModel : null;
                        if (libraryItem5 != null) {
                            id4 = libraryItem5.getId();
                        }
                    } else {
                        id4 = id3;
                    }
                    if (id4 != null) {
                        getPreviewViewModel().onEvent(new MetaPreviewEvent.MarkAsWatched(id4, false));
                    }
                }
                return true;
            default:
                return true;
        }
    }

    static /* synthetic */ void navigate$default(CatalogRowsFragment catalogRowsFragment, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        catalogRowsFragment.navigate(str, z);
    }

    private final void navigate(String deeplink, boolean autoPlay) {
        try {
            getPreviewViewModel().onEvent(MetaPreviewEvent.ClearMetaItem.INSTANCE);
            Uri uri = Uri.parse(DeeplinkUtil.INSTANCE.streamsWithAutoPlay(deeplink, autoPlay));
            NavController navControllerFindNavController = FragmentKt.findNavController(this);
            Intrinsics.checkNotNull(uri);
            navControllerFindNavController.navigate(uri);
        } catch (Exception unused) {
            ContextExtKt.shortToast(this, R.string.label_stremio_tv_meta_link_failed);
        }
    }

    private final Object fromModel(Object itemModel) {
        int iSwitchDispatch$2 = CatalogRowsFragment$$ExternalSyntheticTypeSwitchClass6.switchDispatch$2(itemModel, 0);
        if (iSwitchDispatch$2 == 0) {
            return ((ContinueWatchingItemModel) itemModel).getLibraryItem();
        }
        if (iSwitchDispatch$2 == 1) {
            return ((LibraryItemModel) itemModel).getLibraryItem();
        }
        if (iSwitchDispatch$2 != 2) {
            return null;
        }
        return ((MetaItemModel) itemModel).getMetaItem();
    }
}