package com.stremio.tv.views.board;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.FlowExtKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.NavController;
import androidx.navigation.fragment.FragmentKt;
import com.stremio.common.viewmodels.AnnouncementsViewModel;
import com.stremio.common.viewmodels.BoardViewModel;
import com.stremio.common.viewmodels.CatalogRowsViewModel;
import com.stremio.common.viewmodels.state.Announcement;
import com.stremio.common.viewmodels.state.BoardState;
import com.stremio.common.viewmodels.state.MetaRow;
import com.stremio.common.viewmodels.state.RowsEvent;
import com.stremio.core.Field;
import com.stremio.core.models.Catalog;
import com.stremio.tv.R;
import com.stremio.tv.databinding.DialogAnnouncementBinding;
import com.stremio.tv.views.FocusableFragment;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.core.qualifier.Qualifier;
import org.koin.viewmodel.GetViewModelKt;

/* JADX INFO: compiled from: BoardFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u001a\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0010\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u001dH\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\f\u0010\rR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\t\u001a\u0004\b\u0011\u0010\u0012Ê\u0001\f\b\u001f\u0012\b\b \u0012\u0004\b\u0003\u0010\u0000¨\u0006\u001e"}, d2 = {"Lcom/stremio/tv/views/board/BoardFragment;", "Lcom/stremio/tv/views/FocusableFragment;", "<init>", "()V", "boardViewModel", "Lcom/stremio/common/viewmodels/BoardViewModel;", "getBoardViewModel", "()Lcom/stremio/common/viewmodels/BoardViewModel;", "boardViewModel$delegate", "Lkotlin/Lazy;", "rowsViewModel", "Lcom/stremio/common/viewmodels/CatalogRowsViewModel;", "getRowsViewModel", "()Lcom/stremio/common/viewmodels/CatalogRowsViewModel;", "rowsViewModel$delegate", "announcementsViewModel", "Lcom/stremio/common/viewmodels/AnnouncementsViewModel;", "getAnnouncementsViewModel", "()Lcom/stremio/common/viewmodels/AnnouncementsViewModel;", "announcementsViewModel$delegate", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "Landroid/view/View;", "showAnnouncementDialog", "announcement", "Lcom/stremio/common/viewmodels/state/Announcement;", "Stremio:androidTV-com.stremio.one-1.11.2-40000002_release", "Landroidx/compose/runtime/internal/StabilityInferred;", "parameters"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class BoardFragment extends FocusableFragment {
    public static final int $stable = FocusableFragment.$stable;

    /* JADX INFO: renamed from: announcementsViewModel$delegate, reason: from kotlin metadata */
    private final Lazy announcementsViewModel;

    /* JADX INFO: renamed from: boardViewModel$delegate, reason: from kotlin metadata */
    private final Lazy boardViewModel;

    /* JADX INFO: renamed from: rowsViewModel$delegate, reason: from kotlin metadata */
    private final Lazy rowsViewModel;

    public BoardFragment() {
        super(R.layout.fragment_board);
        final BoardFragment boardFragment = this;
        final Function0<ViewModelStoreOwner> function0 = new Function0<ViewModelStoreOwner>() { // from class: com.stremio.tv.views.board.BoardFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return boardFragment;
            }
        };
        final Qualifier qualifier = null;
        final Function0 function1 = null;
        final Function0 function2 = null;
        this.boardViewModel = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<BoardViewModel>() { // from class: com.stremio.tv.views.board.BoardFragment$special$$inlined$viewModel$default$2
            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r10v4, types: [androidx.lifecycle.ViewModel, com.stremio.common.viewmodels.BoardViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final BoardViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = boardFragment;
                Qualifier qualifier2 = qualifier;
                Function0 function3 = function0;
                Function0 function4 = function1;
                Function0 function5 = function2;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function3.invoke()).getViewModelStore();
                if (function4 == null || (defaultViewModelCreationExtras = (CreationExtras) function4.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "<get-defaultViewModelCreationExtras>(...)");
                }
                return GetViewModelKt.resolveViewModel$default(Reflection.getOrCreateKotlinClass(BoardViewModel.class), viewModelStore, null, defaultViewModelCreationExtras, qualifier2, AndroidKoinScopeExtKt.getKoinScope(fragment), function5, 4, null);
            }
        });
        final Function0<ViewModelStoreOwner> function3 = new Function0<ViewModelStoreOwner>() { // from class: com.stremio.tv.views.board.BoardFragment$special$$inlined$viewModel$default$3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return boardFragment;
            }
        };
        this.rowsViewModel = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<CatalogRowsViewModel>() { // from class: com.stremio.tv.views.board.BoardFragment$special$$inlined$viewModel$default$4
            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r10v4, types: [androidx.lifecycle.ViewModel, com.stremio.common.viewmodels.CatalogRowsViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final CatalogRowsViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = boardFragment;
                Qualifier qualifier2 = qualifier;
                Function0 function4 = function3;
                Function0 function5 = function1;
                Function0 function6 = function2;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function4.invoke()).getViewModelStore();
                if (function5 == null || (defaultViewModelCreationExtras = (CreationExtras) function5.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "<get-defaultViewModelCreationExtras>(...)");
                }
                return GetViewModelKt.resolveViewModel$default(Reflection.getOrCreateKotlinClass(CatalogRowsViewModel.class), viewModelStore, null, defaultViewModelCreationExtras, qualifier2, AndroidKoinScopeExtKt.getKoinScope(fragment), function6, 4, null);
            }
        });
        final Function0<ViewModelStoreOwner> function4 = new Function0<ViewModelStoreOwner>() { // from class: com.stremio.tv.views.board.BoardFragment$special$$inlined$viewModel$default$5
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return boardFragment;
            }
        };
        this.announcementsViewModel = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<AnnouncementsViewModel>() { // from class: com.stremio.tv.views.board.BoardFragment$special$$inlined$viewModel$default$6
            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r10v4, types: [androidx.lifecycle.ViewModel, com.stremio.common.viewmodels.AnnouncementsViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final AnnouncementsViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = boardFragment;
                Qualifier qualifier2 = qualifier;
                Function0 function5 = function4;
                Function0 function6 = function1;
                Function0 function7 = function2;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function5.invoke()).getViewModelStore();
                if (function6 == null || (defaultViewModelCreationExtras = (CreationExtras) function6.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "<get-defaultViewModelCreationExtras>(...)");
                }
                return GetViewModelKt.resolveViewModel$default(Reflection.getOrCreateKotlinClass(AnnouncementsViewModel.class), viewModelStore, null, defaultViewModelCreationExtras, qualifier2, AndroidKoinScopeExtKt.getKoinScope(fragment), function7, 4, null);
            }
        });
    }

    private final BoardViewModel getBoardViewModel() {
        return (BoardViewModel) this.boardViewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CatalogRowsViewModel getRowsViewModel() {
        return (CatalogRowsViewModel) this.rowsViewModel.getValue();
    }

    private final AnnouncementsViewModel getAnnouncementsViewModel() {
        return (AnnouncementsViewModel) this.announcementsViewModel.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getRowsViewModel().setField(Field.BOARD.INSTANCE);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        StateFlow<BoardState> state = getBoardViewModel().getState();
        Lifecycle lifecycle = getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "<get-lifecycle>(...)");
        Flow flowOnEach = FlowKt.onEach(FlowExtKt.flowWithLifecycle(state, lifecycle, Lifecycle.State.STARTED), new AnonymousClass1(null));
        BoardFragment boardFragment = this;
        FlowKt.launchIn(flowOnEach, LifecycleOwnerKt.getLifecycleScope(boardFragment));
        StateFlow<Announcement> state2 = getAnnouncementsViewModel().getState();
        Lifecycle lifecycle2 = getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle2, "<get-lifecycle>(...)");
        FlowKt.launchIn(FlowKt.onEach(FlowKt.filterNotNull(FlowExtKt.flowWithLifecycle(state2, lifecycle2, Lifecycle.State.STARTED)), new AnonymousClass2(null)), LifecycleOwnerKt.getLifecycleScope(boardFragment));
    }

    /* JADX INFO: renamed from: com.stremio.tv.views.board.BoardFragment$onViewCreated$1, reason: invalid class name */
    /* JADX INFO: compiled from: BoardFragment.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "state", "Lcom/stremio/common/viewmodels/state/BoardState;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.tv.views.board.BoardFragment$onViewCreated$1", f = "BoardFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<BoardState, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = BoardFragment.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(BoardState boardState, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(boardState, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            BoardState boardState = (BoardState) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            List listListOf = CollectionsKt.listOf(boardState.getContinueWatching());
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : listListOf) {
                if (!((List) obj2).isEmpty()) {
                    arrayList.add(obj2);
                }
            }
            ArrayList<List> arrayList2 = arrayList;
            BoardFragment boardFragment = BoardFragment.this;
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
            for (List list : arrayList2) {
                String string = boardFragment.getString(R.string.label_continue_watching);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                arrayList3.add(new MetaRow.ContinueWatchingRow(string, list));
            }
            ArrayList arrayList4 = arrayList3;
            List<Catalog> catalogs = boardState.getCatalogs();
            ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(catalogs, 10));
            Iterator<T> it = catalogs.iterator();
            while (it.hasNext()) {
                arrayList5.add(new MetaRow.CatalogRow((Catalog) it.next(), false, false, false, 14, null));
            }
            BoardFragment.this.getRowsViewModel().onEvent(new RowsEvent.LoadRows(CollectionsKt.plus((Collection) arrayList4, (Iterable) arrayList5)));
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.stremio.tv.views.board.BoardFragment$onViewCreated$2, reason: invalid class name */
    /* JADX INFO: compiled from: BoardFragment.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/stremio/common/viewmodels/state/Announcement;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.tv.views.board.BoardFragment$onViewCreated$2", f = "BoardFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<Announcement, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = BoardFragment.this.new AnonymousClass2(continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Announcement announcement, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(announcement, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Announcement announcement = (Announcement) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            BoardFragment.this.showAnnouncementDialog(announcement);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showAnnouncementDialog(Announcement announcement) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        DialogAnnouncementBinding dialogAnnouncementBindingInflate = DialogAnnouncementBinding.inflate(LayoutInflater.from(context));
        dialogAnnouncementBindingInflate.setItem(announcement);
        Intrinsics.checkNotNullExpressionValue(dialogAnnouncementBindingInflate, "apply(...)");
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(context).setView(dialogAnnouncementBindingInflate.getRoot()).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.stremio.tv.views.board.BoardFragment$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                this.f$0.getAnnouncementsViewModel().loadNext();
            }
        }).create();
        Intrinsics.checkNotNullExpressionValue(alertDialogCreate, "create(...)");
        dialogAnnouncementBindingInflate.btnClose.setOnClickListener(new View.OnClickListener() { // from class: com.stremio.tv.views.board.BoardFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                alertDialogCreate.dismiss();
            }
        });
        dialogAnnouncementBindingInflate.btnAddonInstall.setOnClickListener(new View.OnClickListener() { // from class: com.stremio.tv.views.board.BoardFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BoardFragment boardFragment = this.f$0;
                boardFragment.getAnnouncementsViewModel().installAddon(new Function1() { // from class: com.stremio.tv.views.board.BoardFragment$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return BoardFragment.showAnnouncementDialog$lambda$3$0(alertDialog, boardFragment, (String) obj);
                    }
                });
            }
        });
        Window window = alertDialogCreate.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showAnnouncementDialog$lambda$3$0(AlertDialog alertDialog, BoardFragment boardFragment, String str) {
        alertDialog.dismiss();
        if (str != null) {
            NavController navControllerFindNavController = FragmentKt.findNavController(boardFragment);
            Uri uri = Uri.parse(str);
            Intrinsics.checkNotNullExpressionValue(uri, "parse(...)");
            navControllerFindNavController.navigate(uri);
        }
        return Unit.INSTANCE;
    }
}