package com.stremio.tv.views.library;

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
import com.stremio.common.viewmodels.CatalogRowsViewModel;
import com.stremio.common.viewmodels.LibraryViewModel;
import com.stremio.common.viewmodels.state.LibraryState;
import com.stremio.common.viewmodels.state.MetaRow;
import com.stremio.common.viewmodels.state.RowsEvent;
import com.stremio.core.Field;
import com.stremio.core.models.LibraryWithFilters;
import com.stremio.tv.R;
import com.stremio.tv.extensions.PickerExtKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
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

/* JADX INFO: compiled from: LibraryFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000Y\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0014\b\u0007\u0018\u0000 '2\u00020\u0001:\u0001'B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u001a\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u00122\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001c\u001a\u00020\u0017H\u0016J\b\u0010\u001d\u001a\u00020\u0017H\u0002J\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0012\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\"H\u0002J\u0010\u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020&H\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0015Ê\u0001\f\b)\u0012\b\b*\u0012\u0004\b\u0003\u0010\u0000¨\u0006("}, d2 = {"Lcom/stremio/tv/views/library/LibraryFragment;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "libraryViewModel", "Lcom/stremio/common/viewmodels/LibraryViewModel;", "getLibraryViewModel", "()Lcom/stremio/common/viewmodels/LibraryViewModel;", "libraryViewModel$delegate", "Lkotlin/Lazy;", "rowsViewModel", "Lcom/stremio/common/viewmodels/CatalogRowsViewModel;", "getRowsViewModel", "()Lcom/stremio/common/viewmodels/CatalogRowsViewModel;", "rowsViewModel$delegate", "picker", "Landroidx/leanback/widget/picker/Picker;", "rowsView", "Landroid/view/View;", "backPressedCallback", "com/stremio/tv/views/library/LibraryFragment$backPressedCallback$1", "Lcom/stremio/tv/views/library/LibraryFragment$backPressedCallback$1;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "onResume", "updateFocus", "populatePickerColumns", "state", "Lcom/stremio/common/viewmodels/state/LibraryState;", "toTypeLabel", "", "type", "toSortLabel", "sort", "Lcom/stremio/core/models/LibraryWithFilters$Sort;", "Companion", "Stremio:androidTV-com.stremio.one-1.11.2-40000002_release", "Landroidx/compose/runtime/internal/StabilityInferred;", "parameters"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class LibraryFragment extends Fragment {
    private static final int SORT_COLUMN_INDEX = 1;
    private static final int TYPE_COLUMN_INDEX = 0;
    private final LibraryFragment$backPressedCallback$1 backPressedCallback;

    /* JADX INFO: renamed from: libraryViewModel$delegate, reason: from kotlin metadata */
    private final Lazy libraryViewModel;
    private Picker picker;
    private View rowsView;

    /* JADX INFO: renamed from: rowsViewModel$delegate, reason: from kotlin metadata */
    private final Lazy rowsViewModel;
    public static final int $stable = 8;

    /* JADX WARN: Type inference failed for: r0v7, types: [com.stremio.tv.views.library.LibraryFragment$backPressedCallback$1] */
    public LibraryFragment() {
        super(R.layout.fragment_library);
        final LibraryFragment libraryFragment = this;
        final Function0<ViewModelStoreOwner> function0 = new Function0<ViewModelStoreOwner>() { // from class: com.stremio.tv.views.library.LibraryFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return libraryFragment;
            }
        };
        final Qualifier qualifier = null;
        final Function0 function1 = null;
        final Function0 function2 = null;
        this.libraryViewModel = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<LibraryViewModel>() { // from class: com.stremio.tv.views.library.LibraryFragment$special$$inlined$viewModel$default$2
            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r10v4, types: [androidx.lifecycle.ViewModel, com.stremio.common.viewmodels.LibraryViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final LibraryViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = libraryFragment;
                Qualifier qualifier2 = qualifier;
                Function0 function3 = function0;
                Function0 function4 = function1;
                Function0 function5 = function2;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function3.invoke()).getViewModelStore();
                if (function4 == null || (defaultViewModelCreationExtras = (CreationExtras) function4.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "<get-defaultViewModelCreationExtras>(...)");
                }
                return GetViewModelKt.resolveViewModel$default(Reflection.getOrCreateKotlinClass(LibraryViewModel.class), viewModelStore, null, defaultViewModelCreationExtras, qualifier2, AndroidKoinScopeExtKt.getKoinScope(fragment), function5, 4, null);
            }
        });
        final Function0<ViewModelStoreOwner> function3 = new Function0<ViewModelStoreOwner>() { // from class: com.stremio.tv.views.library.LibraryFragment$special$$inlined$viewModel$default$3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return libraryFragment;
            }
        };
        this.rowsViewModel = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<CatalogRowsViewModel>() { // from class: com.stremio.tv.views.library.LibraryFragment$special$$inlined$viewModel$default$4
            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r10v4, types: [androidx.lifecycle.ViewModel, com.stremio.common.viewmodels.CatalogRowsViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final CatalogRowsViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = libraryFragment;
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
        this.backPressedCallback = new OnBackPressedCallback() { // from class: com.stremio.tv.views.library.LibraryFragment$backPressedCallback$1
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

    /* JADX INFO: Access modifiers changed from: private */
    public final LibraryViewModel getLibraryViewModel() {
        return (LibraryViewModel) this.libraryViewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CatalogRowsViewModel getRowsViewModel() {
        return (CatalogRowsViewModel) this.rowsViewModel.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getRowsViewModel().setField(Field.LIBRARY.INSTANCE);
        requireActivity().getOnBackPressedDispatcher().addCallback(this, this.backPressedCallback);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        View viewFindViewById = view.findViewById(R.id.catalog_rows_fragment);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.rowsView = viewFindViewById;
        View viewFindViewById2 = view.findViewById(R.id.library_picker);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
        this.picker = (Picker) viewFindViewById2;
        int i = androidx.leanback.R.layout.lb_picker_column;
        Picker picker = this.picker;
        if (picker == null) {
            Intrinsics.throwUninitializedPropertyAccessException("picker");
            picker = null;
        }
        picker.setSeparators(CollectionsKt.listOf((Object[]) new String[]{"⋮", "⋮", ""}));
        Picker picker2 = this.picker;
        if (picker2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("picker");
            picker2 = null;
        }
        picker2.setColumns(CollectionsKt.listOf((Object[]) new PickerColumn[]{PickerExtKt.emptyColumn(), PickerExtKt.emptyColumn()}));
        Picker picker3 = this.picker;
        if (picker3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("picker");
            picker3 = null;
        }
        picker3.setOnClickListener(new View.OnClickListener() { // from class: com.stremio.tv.views.library.LibraryFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                LibraryFragment.onViewCreated$lambda$0(this.f$0, view2);
            }
        });
        Picker picker4 = this.picker;
        if (picker4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("picker");
            picker4 = null;
        }
        picker4.addOnValueChangedListener(new Picker.PickerValueListener() { // from class: com.stremio.tv.views.library.LibraryFragment$$ExternalSyntheticLambda1
            @Override // androidx.leanback.widget.picker.Picker.PickerValueListener
            public final void onValueChanged(Picker picker5, int i2) {
                LibraryFragment.onViewCreated$lambda$1(this.f$0, picker5, i2);
            }
        });
        StateFlow<LibraryState> state = getLibraryViewModel().getState();
        Lifecycle lifecycle = getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "<get-lifecycle>(...)");
        Flow flowOnEach = FlowKt.onEach(FlowExtKt.flowWithLifecycle(state, lifecycle, Lifecycle.State.STARTED), new AnonymousClass3(null));
        LibraryFragment libraryFragment = this;
        FlowKt.launchIn(flowOnEach, LifecycleOwnerKt.getLifecycleScope(libraryFragment));
        StateFlow<LibraryState> state2 = getLibraryViewModel().getState();
        Lifecycle lifecycle2 = getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle2, "<get-lifecycle>(...)");
        FlowKt.launchIn(FlowKt.onEach(FlowKt.distinctUntilChanged(FlowExtKt.flowWithLifecycle(state2, lifecycle2, Lifecycle.State.STARTED), new Function2() { // from class: com.stremio.tv.views.library.LibraryFragment$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(LibraryFragment.onViewCreated$lambda$2((LibraryState) obj, (LibraryState) obj2));
            }
        }), new AnonymousClass5(view, this, null)), LifecycleOwnerKt.getLifecycleScope(libraryFragment));
        getLibraryViewModel().load();
    }

    static final void onViewCreated$lambda$0(LibraryFragment libraryFragment, View view) {
        Picker picker = libraryFragment.picker;
        Picker picker2 = null;
        if (picker == null) {
            Intrinsics.throwUninitializedPropertyAccessException("picker");
            picker = null;
        }
        Picker picker3 = libraryFragment.picker;
        if (picker3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("picker");
            picker3 = null;
        }
        picker.setActivated(!picker3.isActivated());
        LibraryFragment$backPressedCallback$1 libraryFragment$backPressedCallback$1 = libraryFragment.backPressedCallback;
        Picker picker4 = libraryFragment.picker;
        if (picker4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("picker");
        } else {
            picker2 = picker4;
        }
        libraryFragment$backPressedCallback$1.setEnabled(picker2.isActivated());
        libraryFragment.updateFocus();
    }

    static final void onViewCreated$lambda$1(LibraryFragment libraryFragment, Picker picker, int i) {
        Intrinsics.checkNotNullParameter(picker, "picker");
        LibraryState value = libraryFragment.getLibraryViewModel().getState().getValue();
        PickerColumn columnAt = picker.getColumnAt(i);
        int currentValue = columnAt != null ? columnAt.getCurrentValue() : 0;
        if (i == 0) {
            libraryFragment.getLibraryViewModel().loadType(value.getTypes().get(currentValue).getType());
        }
        if (i == 1) {
            libraryFragment.getLibraryViewModel().loadSort(value.getSorts().get(currentValue).getSort());
        }
    }

    /* JADX INFO: renamed from: com.stremio.tv.views.library.LibraryFragment$onViewCreated$3, reason: invalid class name */
    /* JADX INFO: compiled from: LibraryFragment.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "state", "Lcom/stremio/common/viewmodels/state/LibraryState;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.tv.views.library.LibraryFragment$onViewCreated$3", f = "LibraryFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass3 extends SuspendLambda implements Function2<LibraryState, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 anonymousClass3 = LibraryFragment.this.new AnonymousClass3(continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(LibraryState libraryState, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(libraryState, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            LibraryState libraryState = (LibraryState) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            String type = libraryState.getType();
            if (type == null) {
                type = "none";
            }
            LibraryFragment.this.getRowsViewModel().onEvent(new RowsEvent.LoadRows(CollectionsKt.listOf(new MetaRow.LibraryRow(type, libraryState.getCatalog()))));
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.stremio.tv.views.library.LibraryFragment$onViewCreated$5, reason: invalid class name */
    /* JADX INFO: compiled from: LibraryFragment.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/stremio/common/viewmodels/state/LibraryState;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.tv.views.library.LibraryFragment$onViewCreated$5", f = "LibraryFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass5 extends SuspendLambda implements Function2<LibraryState, Continuation<? super Unit>, Object> {
        final /* synthetic */ View $view;
        int label;
        final /* synthetic */ LibraryFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass5(View view, LibraryFragment libraryFragment, Continuation<? super AnonymousClass5> continuation) {
            super(2, continuation);
            this.$view = view;
            this.this$0 = libraryFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass5(this.$view, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(LibraryState libraryState, Continuation<? super Unit> continuation) {
            return ((AnonymousClass5) create(libraryState, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            View view = this.$view;
            final LibraryFragment libraryFragment = this.this$0;
            view.post(new Runnable() { // from class: com.stremio.tv.views.library.LibraryFragment$onViewCreated$5$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    LibraryFragment libraryFragment2 = libraryFragment;
                    libraryFragment2.populatePickerColumns(libraryFragment2.getLibraryViewModel().getState().getValue());
                }
            });
            return Unit.INSTANCE;
        }
    }

    static final boolean onViewCreated$lambda$2(LibraryState old, LibraryState libraryState) {
        Intrinsics.checkNotNullParameter(old, "old");
        Intrinsics.checkNotNullParameter(libraryState, "new");
        return Intrinsics.areEqual(old.getSort(), libraryState.getSort()) && Intrinsics.areEqual(old.getSorts(), libraryState.getSorts());
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        updateFocus();
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
    public final void populatePickerColumns(LibraryState state) {
        int i;
        List<LibraryWithFilters.SelectableType> types = state.getTypes();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(types, 10));
        Iterator<T> it = types.iterator();
        while (it.hasNext()) {
            arrayList.add(toTypeLabel(((LibraryWithFilters.SelectableType) it.next()).getType()));
        }
        int i2 = 0;
        String[] strArr = (String[]) arrayList.toArray(new String[0]);
        Iterator<LibraryWithFilters.SelectableType> it2 = state.getTypes().iterator();
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
        List<LibraryWithFilters.SelectableSort> sorts = state.getSorts();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(sorts, 10));
        Iterator<T> it3 = sorts.iterator();
        while (it3.hasNext()) {
            arrayList2.add(toSortLabel(((LibraryWithFilters.SelectableSort) it3.next()).getSort()));
        }
        String[] strArr2 = (String[]) arrayList2.toArray(new String[0]);
        Iterator<LibraryWithFilters.SelectableSort> it4 = state.getSorts().iterator();
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
        } else {
            picker2 = picker3;
        }
        PickerExtKt.updateColumn(picker2, 1, strArr2, i);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:37:0x007e  */
    /* JADX WARN: Code duplicated, block: B:39:0x0084  */
    private final String toTypeLabel(String type) {
        Context context;
        String string = null;
        if (type != null) {
            switch (type) {
                case "series":
                    Context context2 = getContext();
                    if (context2 != null) {
                        string = context2.getString(R.string.label_type_series_pl);
                    }
                    break;
                case "tv":
                    Context context3 = getContext();
                    if (context3 != null) {
                        string = context3.getString(R.string.label_type_tv_pl);
                    }
                    break;
                case "movie":
                    Context context4 = getContext();
                    if (context4 != null) {
                        string = context4.getString(R.string.label_type_movie_pl);
                    }
                    break;
                case "other":
                    Context context5 = getContext();
                    if (context5 != null) {
                        string = context5.getString(R.string.label_type_other_pl);
                    }
                    break;
                case "channel":
                    Context context6 = getContext();
                    if (context6 != null) {
                        string = context6.getString(R.string.label_type_channel_pl);
                    }
                    break;
                default:
                    context = getContext();
                    if (context != null) {
                        string = context.getString(R.string.label_type_all);
                    }
                    break;
            }
        } else {
            context = getContext();
            if (context != null) {
                string = context.getString(R.string.label_type_all);
            }
        }
        return string == null ? "None" : string;
    }

    private final String toSortLabel(LibraryWithFilters.Sort sort) {
        String string = null;
        if (Intrinsics.areEqual(sort, LibraryWithFilters.Sort.LAST_WATCHED.INSTANCE)) {
            Context context = getContext();
            if (context != null) {
                string = context.getString(R.string.label_stremio_tv_library_sort_last_watched);
            }
        } else if (Intrinsics.areEqual(sort, LibraryWithFilters.Sort.TIMES_WATCHED.INSTANCE)) {
            Context context2 = getContext();
            if (context2 != null) {
                string = context2.getString(R.string.label_stremio_tv_library_sort_times_watched);
            }
        } else if (Intrinsics.areEqual(sort, LibraryWithFilters.Sort.NAME.INSTANCE)) {
            Context context3 = getContext();
            if (context3 != null) {
                string = context3.getString(R.string.label_stremio_tv_library_sort_name);
            }
        } else if (Intrinsics.areEqual(sort, LibraryWithFilters.Sort.NAME_REVERSE.INSTANCE)) {
            Context context4 = getContext();
            if (context4 != null) {
                string = context4.getString(R.string.label_stremio_tv_library_sort_name_reverse);
            }
        } else if (Intrinsics.areEqual(sort, LibraryWithFilters.Sort.NOT_WATCHED.INSTANCE)) {
            Context context5 = getContext();
            if (context5 != null) {
                string = context5.getString(R.string.label_stremio_tv_library_sort_not_watched);
            }
        } else if (Intrinsics.areEqual(sort, LibraryWithFilters.Sort.WATCHED.INSTANCE)) {
            Context context6 = getContext();
            if (context6 != null) {
                string = context6.getString(R.string.label_stremio_tv_library_sort_watched);
            }
        } else if (!(sort instanceof LibraryWithFilters.Sort.UNRECOGNIZED)) {
            throw new NoWhenBranchMatchedException();
        }
        return string == null ? sort.toString() : string;
    }
}