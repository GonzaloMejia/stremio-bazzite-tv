package com.stremio.tv.views.discover;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.leanback.widget.picker.Picker;
import androidx.leanback.widget.picker.PickerColumn;
import androidx.lifecycle.FlowExtKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.NavArgsLazy;
import com.stremio.common.extensions.ContextExtKt;
import com.stremio.common.viewmodels.CatalogRowsViewModel;
import com.stremio.common.viewmodels.DiscoverViewModel;
import com.stremio.common.viewmodels.state.DiscoverState;
import com.stremio.common.viewmodels.state.MetaRow;
import com.stremio.common.viewmodels.state.RowsEvent;
import com.stremio.core.Field;
import com.stremio.core.models.Catalog;
import com.stremio.core.models.CatalogWithFilters;
import com.stremio.core.types.addon.ResourceRequest;
import com.stremio.tv.R;
import com.stremio.tv.extensions.PickerExtKt;
import com.stremio.tv.util.LoggingUtil;
import java.util.ArrayList;
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
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.core.qualifier.Qualifier;
import org.koin.viewmodel.GetViewModelKt;

/* JADX INFO: compiled from: DiscoverFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000S\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u001a\b\u0007\u0018\u0000 '2\u00020\u0001:\u0001'B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u001a\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\u00182\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010\"\u001a\u00020\u001dH\u0016J\b\u0010#\u001a\u00020\u001dH\u0002J\u0010\u0010$\u001a\u00020\u001d2\u0006\u0010%\u001a\u00020&H\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001bÊ\u0001\f\b)\u0012\b\b*\u0012\u0004\b\u0003\u0010\u0000¨\u0006("}, d2 = {"Lcom/stremio/tv/views/discover/DiscoverFragment;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "args", "Lcom/stremio/tv/views/discover/DiscoverFragmentArgs;", "getArgs", "()Lcom/stremio/tv/views/discover/DiscoverFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "discoverViewModel", "Lcom/stremio/common/viewmodels/DiscoverViewModel;", "getDiscoverViewModel", "()Lcom/stremio/common/viewmodels/DiscoverViewModel;", "discoverViewModel$delegate", "Lkotlin/Lazy;", "rowsViewModel", "Lcom/stremio/common/viewmodels/CatalogRowsViewModel;", "getRowsViewModel", "()Lcom/stremio/common/viewmodels/CatalogRowsViewModel;", "rowsViewModel$delegate", "picker", "Landroidx/leanback/widget/picker/Picker;", "rowsView", "Landroid/view/View;", "backPressedCallback", "com/stremio/tv/views/discover/DiscoverFragment$backPressedCallback$1", "Lcom/stremio/tv/views/discover/DiscoverFragment$backPressedCallback$1;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "onResume", "updateFocus", "populatePickerColumns", "state", "Lcom/stremio/common/viewmodels/state/DiscoverState;", "Companion", "Stremio:androidTV-com.stremio.one-1.11.2-40000002_release", "Landroidx/compose/runtime/internal/StabilityInferred;", "parameters"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class DiscoverFragment extends Fragment {
    private static final int CATALOG_COLUMN_INDEX = 1;
    private static final int GENRE_COLUMN_INDEX = 2;
    private static final int TYPE_COLUMN_INDEX = 0;

    /* JADX INFO: renamed from: args$delegate, reason: from kotlin metadata */
    private final NavArgsLazy args;
    private final DiscoverFragment$backPressedCallback$1 backPressedCallback;

    /* JADX INFO: renamed from: discoverViewModel$delegate, reason: from kotlin metadata */
    private final Lazy discoverViewModel;
    private Picker picker;
    private View rowsView;

    /* JADX INFO: renamed from: rowsViewModel$delegate, reason: from kotlin metadata */
    private final Lazy rowsViewModel;
    public static final int $stable = 8;

    /* JADX WARN: Type inference failed for: r0v8, types: [com.stremio.tv.views.discover.DiscoverFragment$backPressedCallback$1] */
    public DiscoverFragment() {
        super(R.layout.fragment_discover);
        final DiscoverFragment discoverFragment = this;
        this.args = new NavArgsLazy(Reflection.getOrCreateKotlinClass(DiscoverFragmentArgs.class), new Function0<Bundle>() { // from class: com.stremio.tv.views.discover.DiscoverFragment$special$$inlined$navArgs$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Bundle invoke() {
                Bundle arguments = discoverFragment.getArguments();
                if (arguments != null) {
                    return arguments;
                }
                throw new IllegalStateException("Fragment " + discoverFragment + " has null arguments");
            }
        });
        final Function0<ViewModelStoreOwner> function0 = new Function0<ViewModelStoreOwner>() { // from class: com.stremio.tv.views.discover.DiscoverFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return discoverFragment;
            }
        };
        final Qualifier qualifier = null;
        final Function0 function1 = null;
        final Function0 function2 = null;
        this.discoverViewModel = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<DiscoverViewModel>() { // from class: com.stremio.tv.views.discover.DiscoverFragment$special$$inlined$viewModel$default$2
            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r10v4, types: [androidx.lifecycle.ViewModel, com.stremio.common.viewmodels.DiscoverViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final DiscoverViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = discoverFragment;
                Qualifier qualifier2 = qualifier;
                Function0 function3 = function0;
                Function0 function4 = function1;
                Function0 function5 = function2;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function3.invoke()).getViewModelStore();
                if (function4 == null || (defaultViewModelCreationExtras = (CreationExtras) function4.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "<get-defaultViewModelCreationExtras>(...)");
                }
                return GetViewModelKt.resolveViewModel$default(Reflection.getOrCreateKotlinClass(DiscoverViewModel.class), viewModelStore, null, defaultViewModelCreationExtras, qualifier2, AndroidKoinScopeExtKt.getKoinScope(fragment), function5, 4, null);
            }
        });
        final Function0<ViewModelStoreOwner> function3 = new Function0<ViewModelStoreOwner>() { // from class: com.stremio.tv.views.discover.DiscoverFragment$special$$inlined$viewModel$default$3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return discoverFragment;
            }
        };
        this.rowsViewModel = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<CatalogRowsViewModel>() { // from class: com.stremio.tv.views.discover.DiscoverFragment$special$$inlined$viewModel$default$4
            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r10v4, types: [androidx.lifecycle.ViewModel, com.stremio.common.viewmodels.CatalogRowsViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final CatalogRowsViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = discoverFragment;
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
        this.backPressedCallback = new OnBackPressedCallback() { // from class: com.stremio.tv.views.discover.DiscoverFragment$backPressedCallback$1
            {
                super(false);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                Picker picker = this.this$0.picker;
                if (picker == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("picker");
                    picker = null;
                }
                picker.performClick();
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final DiscoverFragmentArgs getArgs() {
        return (DiscoverFragmentArgs) this.args.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DiscoverViewModel getDiscoverViewModel() {
        return (DiscoverViewModel) this.discoverViewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CatalogRowsViewModel getRowsViewModel() {
        return (CatalogRowsViewModel) this.rowsViewModel.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getRowsViewModel().setField(Field.DISCOVER.INSTANCE);
        getDiscoverViewModel().loadCatalog(getArgs().getCatalogAddonUrl(), getArgs().getType(), getArgs().getId(), getArgs().getGenre());
        requireActivity().getOnBackPressedDispatcher().addCallback(this, this.backPressedCallback);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        View viewFindViewById = view.findViewById(R.id.catalog_rows_fragment);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.rowsView = viewFindViewById;
        View viewFindViewById2 = view.findViewById(R.id.discover_picker);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
        Picker picker = (Picker) viewFindViewById2;
        this.picker = picker;
        if (picker == null) {
            Intrinsics.throwUninitializedPropertyAccessException("picker");
            picker = null;
        }
        picker.setSeparators(CollectionsKt.listOf((Object[]) new String[]{"⋮", "⋮", "⋮", ""}));
        Picker picker2 = this.picker;
        if (picker2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("picker");
            picker2 = null;
        }
        picker2.setColumns(CollectionsKt.listOf((Object[]) new PickerColumn[]{PickerExtKt.emptyColumn(), PickerExtKt.emptyColumn(), PickerExtKt.emptyColumn()}));
        Picker picker3 = this.picker;
        if (picker3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("picker");
            picker3 = null;
        }
        picker3.setOnClickListener(new View.OnClickListener() { // from class: com.stremio.tv.views.discover.DiscoverFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                DiscoverFragment.onViewCreated$lambda$0(this.f$0, view2);
            }
        });
        Picker picker4 = this.picker;
        if (picker4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("picker");
            picker4 = null;
        }
        picker4.addOnValueChangedListener(new Picker.PickerValueListener() { // from class: com.stremio.tv.views.discover.DiscoverFragment$$ExternalSyntheticLambda1
            @Override // androidx.leanback.widget.picker.Picker.PickerValueListener
            public final void onValueChanged(Picker picker5, int i) {
                DiscoverFragment.onViewCreated$lambda$1(this.f$0, picker5, i);
            }
        });
        StateFlow<DiscoverState> state = getDiscoverViewModel().getState();
        Lifecycle lifecycle = getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "<get-lifecycle>(...)");
        Flow flowOnEach = FlowKt.onEach(FlowExtKt.flowWithLifecycle(state, lifecycle, Lifecycle.State.STARTED), new AnonymousClass3(null));
        DiscoverFragment discoverFragment = this;
        FlowKt.launchIn(flowOnEach, LifecycleOwnerKt.getLifecycleScope(discoverFragment));
        StateFlow<DiscoverState> state2 = getDiscoverViewModel().getState();
        Lifecycle lifecycle2 = getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle2, "<get-lifecycle>(...)");
        FlowKt.launchIn(FlowKt.onEach(FlowKt.distinctUntilChanged(FlowExtKt.flowWithLifecycle(state2, lifecycle2, Lifecycle.State.STARTED), new Function2() { // from class: com.stremio.tv.views.discover.DiscoverFragment$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(DiscoverFragment.onViewCreated$lambda$2((DiscoverState) obj, (DiscoverState) obj2));
            }
        }), new AnonymousClass5(view, this, null)), LifecycleOwnerKt.getLifecycleScope(discoverFragment));
    }

    static final void onViewCreated$lambda$0(DiscoverFragment discoverFragment, View view) {
        Picker picker = discoverFragment.picker;
        Picker picker2 = null;
        if (picker == null) {
            Intrinsics.throwUninitializedPropertyAccessException("picker");
            picker = null;
        }
        Picker picker3 = discoverFragment.picker;
        if (picker3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("picker");
            picker3 = null;
        }
        picker.setActivated(!picker3.isActivated());
        DiscoverFragment$backPressedCallback$1 discoverFragment$backPressedCallback$1 = discoverFragment.backPressedCallback;
        Picker picker4 = discoverFragment.picker;
        if (picker4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("picker");
        } else {
            picker2 = picker4;
        }
        discoverFragment$backPressedCallback$1.setEnabled(picker2.isActivated());
        discoverFragment.updateFocus();
    }

    static final void onViewCreated$lambda$1(DiscoverFragment discoverFragment, Picker picker, int i) {
        ResourceRequest request;
        CatalogWithFilters.SelectableExtra genres;
        List<CatalogWithFilters.SelectableExtraOption> options;
        CatalogWithFilters.SelectableExtraOption selectableExtraOption;
        Intrinsics.checkNotNullParameter(picker, "picker");
        DiscoverState value = discoverFragment.getDiscoverViewModel().getState().getValue();
        PickerColumn columnAt = picker.getColumnAt(i);
        int currentValue = columnAt != null ? columnAt.getCurrentValue() : 0;
        if (i == 0) {
            request = value.getTypes().get(currentValue).getRequest();
        } else if (i != 1) {
            request = null;
            if (i == 2 && (genres = value.getGenres()) != null && (options = genres.getOptions()) != null && (selectableExtraOption = options.get(currentValue)) != null) {
                request = selectableExtraOption.getRequest();
            }
        } else {
            request = value.getCatalogs().get(currentValue).getRequest();
        }
        if (request != null) {
            discoverFragment.getDiscoverViewModel().loadCatalog(request);
        }
    }

    /* JADX INFO: renamed from: com.stremio.tv.views.discover.DiscoverFragment$onViewCreated$3, reason: invalid class name */
    /* JADX INFO: compiled from: DiscoverFragment.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "state", "Lcom/stremio/common/viewmodels/state/DiscoverState;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.tv.views.discover.DiscoverFragment$onViewCreated$3", f = "DiscoverFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass3 extends SuspendLambda implements Function2<DiscoverState, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 anonymousClass3 = DiscoverFragment.this.new AnonymousClass3(continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(DiscoverState discoverState, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(discoverState, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            DiscoverState discoverState = (DiscoverState) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Catalog catalog = discoverState.getCatalog();
            if (catalog != null) {
                List listListOf = CollectionsKt.listOf(new MetaRow.CatalogRow(catalog, true, false, false, 8, null));
                if (listListOf != null) {
                    DiscoverFragment.this.getRowsViewModel().onEvent(new RowsEvent.LoadRows(listListOf));
                }
            }
            return Unit.INSTANCE;
        }
    }

    static final boolean onViewCreated$lambda$2(DiscoverState old, DiscoverState discoverState) {
        Intrinsics.checkNotNullParameter(old, "old");
        Intrinsics.checkNotNullParameter(discoverState, "new");
        return Intrinsics.areEqual(old.getTypes(), discoverState.getTypes()) && Intrinsics.areEqual(old.getCatalogs(), discoverState.getCatalogs()) && Intrinsics.areEqual(old.getGenres(), discoverState.getGenres());
    }

    /* JADX INFO: renamed from: com.stremio.tv.views.discover.DiscoverFragment$onViewCreated$5, reason: invalid class name */
    /* JADX INFO: compiled from: DiscoverFragment.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/stremio/common/viewmodels/state/DiscoverState;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.tv.views.discover.DiscoverFragment$onViewCreated$5", f = "DiscoverFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass5 extends SuspendLambda implements Function2<DiscoverState, Continuation<? super Unit>, Object> {
        final /* synthetic */ View $view;
        int label;
        final /* synthetic */ DiscoverFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass5(View view, DiscoverFragment discoverFragment, Continuation<? super AnonymousClass5> continuation) {
            super(2, continuation);
            this.$view = view;
            this.this$0 = discoverFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass5(this.$view, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(DiscoverState discoverState, Continuation<? super Unit> continuation) {
            return ((AnonymousClass5) create(discoverState, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            View view = this.$view;
            final DiscoverFragment discoverFragment = this.this$0;
            view.post(new Runnable() { // from class: com.stremio.tv.views.discover.DiscoverFragment$onViewCreated$5$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DiscoverFragment discoverFragment2 = discoverFragment;
                    discoverFragment2.populatePickerColumns(discoverFragment2.getDiscoverViewModel().getState().getValue());
                }
            });
            return Unit.INSTANCE;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        updateFocus();
        LoggingUtil loggingUtil = LoggingUtil.INSTANCE;
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
        loggingUtil.setScreenName(simpleName);
    }

    private final void updateFocus() {
        Picker picker = this.picker;
        View view = null;
        if (picker == null) {
            Intrinsics.throwUninitializedPropertyAccessException("picker");
            picker = null;
        }
        if (picker.isActivated()) {
            return;
        }
        View view2 = this.rowsView;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rowsView");
        } else {
            view = view2;
        }
        view.requestFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void populatePickerColumns(DiscoverState state) {
        int i;
        List<CatalogWithFilters.SelectableType> types = state.getTypes();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(types, 10));
        Iterator<T> it = types.iterator();
        while (it.hasNext()) {
            arrayList.add(ContextExtKt.toDisplayMetaType(((CatalogWithFilters.SelectableType) it.next()).getType(), getContext()));
        }
        int i2 = 0;
        String[] strArr = (String[]) arrayList.toArray(new String[0]);
        Iterator<CatalogWithFilters.SelectableType> it2 = state.getTypes().iterator();
        int i3 = 0;
        while (true) {
            i = -1;
            if (!it2.hasNext()) {
                i3 = -1;
                break;
            } else if (it2.next().getSelected()) {
                break;
            } else {
                i3++;
            }
        }
        Picker picker = this.picker;
        Picker picker2 = null;
        if (picker == null) {
            Intrinsics.throwUninitializedPropertyAccessException("picker");
            picker = null;
        }
        PickerExtKt.updateColumn(picker, 0, strArr, i3);
        List<CatalogWithFilters.SelectableCatalog> catalogs = state.getCatalogs();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(catalogs, 10));
        Iterator<T> it3 = catalogs.iterator();
        while (it3.hasNext()) {
            arrayList2.add(((CatalogWithFilters.SelectableCatalog) it3.next()).getName());
        }
        String[] strArr2 = (String[]) arrayList2.toArray(new String[0]);
        Iterator<CatalogWithFilters.SelectableCatalog> it4 = state.getCatalogs().iterator();
        while (it4.hasNext()) {
            if (it4.next().getSelected()) {
                i = i2;
                break;
            }
            i2++;
        }
        Picker picker3 = this.picker;
        if (picker3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("picker");
            picker3 = null;
        }
        PickerExtKt.updateColumn(picker3, 1, strArr2, i);
        Context context = getContext();
        String string = context != null ? context.getString(R.string.label_stremio_tv_discover_genre_default) : null;
        if (string == null) {
            string = "";
        }
        String[] genreLabels = state.getGenreLabels(string);
        int selectedGenre = state.getSelectedGenre();
        Picker picker4 = this.picker;
        if (picker4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("picker");
        } else {
            picker2 = picker4;
        }
        PickerExtKt.updateColumn(picker2, 2, genreLabels, selectedGenre);
    }
}