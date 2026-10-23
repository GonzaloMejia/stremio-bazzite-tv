package com.stremio.tv.views.addons;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.leanback.app.VerticalGridSupportFragment;
import androidx.leanback.widget.BrowseFrameLayout;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.OnItemViewSelectedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.leanback.widget.picker.Picker;
import androidx.leanback.widget.picker.PickerColumn;
import androidx.lifecycle.FlowExtKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.NavArgsLazy;
import androidx.navigation.fragment.FragmentKt;
import com.google.android.material.textfield.TextInputLayout;
import com.stremio.common.extensions.ContextExtKt;
import com.stremio.common.extensions.LoadableExtKt;
import com.stremio.common.viewmodels.AddonsViewModel;
import com.stremio.common.viewmodels.state.AddonsState;
import com.stremio.core.models.AddonsWithFilters;
import com.stremio.core.models.LoadableAddonCatalog;
import com.stremio.core.types.addon.AddonDescriptor;
import com.stremio.core.types.addon.ResourceRequest;
import com.stremio.tv.R;
import com.stremio.tv.extensions.PickerExtKt;
import com.stremio.tv.util.BindingUtils;
import com.stremio.tv.views.FocusableFragment;
import com.stremio.tv.views.addons.model.DescriptorModel;
import com.stremio.tv.widget.FixedCenterVerticalGridPresenter;
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

/* JADX INFO: compiled from: AddonsFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000k\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000*\u0001\u001a\b\u0007\u0018\u0000 12\u00020\u0001:\u00011B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u001a\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010#\u001a\u00020\u001dH\u0016J\b\u0010$\u001a\u00020\u001dH\u0002J\u0010\u0010%\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020'H\u0002J\b\u0010(\u001a\u00020\u001dH\u0002J\u0012\u0010)\u001a\u00020\u001d2\b\u0010*\u001a\u0004\u0018\u00010+H\u0002J\u0010\u0010,\u001a\u00020\u001d2\u0006\u0010-\u001a\u00020.H\u0002J\u0010\u0010/\u001a\u00020'2\u0006\u00100\u001a\u00020'H\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001bÊ\u0001\f\b3\u0012\b\b4\u0012\u0004\b\u0003\u0010\u0000¨\u00062"}, d2 = {"Lcom/stremio/tv/views/addons/AddonsFragment;", "Lcom/stremio/tv/views/FocusableFragment;", "<init>", "()V", "args", "Lcom/stremio/tv/views/addons/AddonsWrapperFragmentArgs;", "getArgs", "()Lcom/stremio/tv/views/addons/AddonsWrapperFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "viewModel", "Lcom/stremio/common/viewmodels/AddonsViewModel;", "getViewModel", "()Lcom/stremio/common/viewmodels/AddonsViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "controller", "Lcom/stremio/tv/views/addons/AddonsController;", "getController", "()Lcom/stremio/tv/views/addons/AddonsController;", "controller$delegate", "picker", "Landroidx/leanback/widget/picker/Picker;", "gridFragment", "Landroidx/leanback/app/VerticalGridSupportFragment;", "backPressedCallback", "com/stremio/tv/views/addons/AddonsFragment$backPressedCallback$1", "Lcom/stremio/tv/views/addons/AddonsFragment$backPressedCallback$1;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "Landroid/view/View;", "onResume", "updateFocus", "navigateToAddonDetails", "transportUrl", "", "showAddAddonDialog", "updateAddonCatalog", "catalog", "Lcom/stremio/core/models/LoadableAddonCatalog;", "populatePickerColumns", "state", "Lcom/stremio/common/viewmodels/state/AddonsState;", "toDisplayCatalogName", "name", "Companion", "Stremio:androidTV-com.stremio.one-1.11.2-40000002_release", "Landroidx/compose/runtime/internal/StabilityInferred;", "parameters"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class AddonsFragment extends FocusableFragment {
    private static final int CATALOG_COLUMN_INDEX = 0;
    private static final int TYPE_COLUMN_INDEX = 1;

    /* JADX INFO: renamed from: args$delegate, reason: from kotlin metadata */
    private final NavArgsLazy args;
    private final AddonsFragment$backPressedCallback$1 backPressedCallback;

    /* JADX INFO: renamed from: controller$delegate, reason: from kotlin metadata */
    private final Lazy controller;
    private VerticalGridSupportFragment gridFragment;
    private Picker picker;

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;
    public static final int $stable = 8;

    /* JADX WARN: Type inference failed for: r0v7, types: [com.stremio.tv.views.addons.AddonsFragment$backPressedCallback$1] */
    public AddonsFragment() {
        super(R.layout.fragment_addons);
        final AddonsFragment addonsFragment = this;
        this.args = new NavArgsLazy(Reflection.getOrCreateKotlinClass(AddonsWrapperFragmentArgs.class), new Function0<Bundle>() { // from class: com.stremio.tv.views.addons.AddonsFragment$special$$inlined$navArgs$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Bundle invoke() {
                Bundle arguments = addonsFragment.getArguments();
                if (arguments != null) {
                    return arguments;
                }
                throw new IllegalStateException("Fragment " + addonsFragment + " has null arguments");
            }
        });
        final Function0<ViewModelStoreOwner> function0 = new Function0<ViewModelStoreOwner>() { // from class: com.stremio.tv.views.addons.AddonsFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return addonsFragment;
            }
        };
        final Qualifier qualifier = null;
        final Function0 function1 = null;
        final Function0 function2 = null;
        this.viewModel = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<AddonsViewModel>() { // from class: com.stremio.tv.views.addons.AddonsFragment$special$$inlined$viewModel$default$2
            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r10v4, types: [androidx.lifecycle.ViewModel, com.stremio.common.viewmodels.AddonsViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final AddonsViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = addonsFragment;
                Qualifier qualifier2 = qualifier;
                Function0 function3 = function0;
                Function0 function4 = function1;
                Function0 function5 = function2;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function3.invoke()).getViewModelStore();
                if (function4 == null || (defaultViewModelCreationExtras = (CreationExtras) function4.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "<get-defaultViewModelCreationExtras>(...)");
                }
                return GetViewModelKt.resolveViewModel$default(Reflection.getOrCreateKotlinClass(AddonsViewModel.class), viewModelStore, null, defaultViewModelCreationExtras, qualifier2, AndroidKoinScopeExtKt.getKoinScope(fragment), function5, 4, null);
            }
        });
        this.controller = LazyKt.lazy(new Function0() { // from class: com.stremio.tv.views.addons.AddonsFragment$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AddonsFragment.controller_delegate$lambda$0(this.f$0);
            }
        });
        this.backPressedCallback = new OnBackPressedCallback() { // from class: com.stremio.tv.views.addons.AddonsFragment$backPressedCallback$1
            {
                super(false);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                Picker picker = this.this$0.picker;
                VerticalGridSupportFragment verticalGridSupportFragment = null;
                Picker picker2 = null;
                if (picker == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("picker");
                    picker = null;
                }
                boolean zIsActivated = picker.isActivated();
                AddonsFragment addonsFragment2 = this.this$0;
                if (zIsActivated) {
                    Picker picker3 = addonsFragment2.picker;
                    if (picker3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("picker");
                    } else {
                        picker2 = picker3;
                    }
                    picker2.performClick();
                    return;
                }
                VerticalGridSupportFragment verticalGridSupportFragment2 = addonsFragment2.gridFragment;
                if (verticalGridSupportFragment2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("gridFragment");
                } else {
                    verticalGridSupportFragment = verticalGridSupportFragment2;
                }
                verticalGridSupportFragment.setSelectedPosition(0);
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final AddonsWrapperFragmentArgs getArgs() {
        return (AddonsWrapperFragmentArgs) this.args.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AddonsViewModel getViewModel() {
        return (AddonsViewModel) this.viewModel.getValue();
    }

    static final AddonsController controller_delegate$lambda$0(AddonsFragment addonsFragment) {
        Lifecycle lifecycle = addonsFragment.getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "<get-lifecycle>(...)");
        return new AddonsController(lifecycle);
    }

    private final AddonsController getController() {
        return (AddonsController) this.controller.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().loadAddons(getArgs().getType(), getArgs().getAddonUrl(), getArgs().getCatalogId());
        requireActivity().getOnBackPressedDispatcher().addCallback(this, this.backPressedCallback);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        Fragment fragmentFindFragmentById = getChildFragmentManager().findFragmentById(R.id.addons_list_fragment);
        Intrinsics.checkNotNull(fragmentFindFragmentById, "null cannot be cast to non-null type androidx.leanback.app.VerticalGridSupportFragment");
        VerticalGridSupportFragment verticalGridSupportFragment = (VerticalGridSupportFragment) fragmentFindFragmentById;
        this.gridFragment = verticalGridSupportFragment;
        if (verticalGridSupportFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("gridFragment");
            verticalGridSupportFragment = null;
        }
        verticalGridSupportFragment.setAdapter(getController().getAdapter());
        VerticalGridSupportFragment verticalGridSupportFragment2 = this.gridFragment;
        if (verticalGridSupportFragment2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("gridFragment");
            verticalGridSupportFragment2 = null;
        }
        FixedCenterVerticalGridPresenter fixedCenterVerticalGridPresenter = new FixedCenterVerticalGridPresenter(1, false, 2, null);
        fixedCenterVerticalGridPresenter.setNumberOfColumns(1);
        verticalGridSupportFragment2.setGridPresenter(fixedCenterVerticalGridPresenter);
        VerticalGridSupportFragment verticalGridSupportFragment3 = this.gridFragment;
        if (verticalGridSupportFragment3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("gridFragment");
            verticalGridSupportFragment3 = null;
        }
        verticalGridSupportFragment3.setOnItemViewSelectedListener(new OnItemViewSelectedListener() { // from class: com.stremio.tv.views.addons.AddonsFragment$$ExternalSyntheticLambda1
            @Override // androidx.leanback.widget.BaseOnItemViewSelectedListener
            public final void onItemSelected(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Row row) {
                AddonsFragment.onViewCreated$lambda$1(this.f$0, viewHolder, obj, viewHolder2, row);
            }
        });
        VerticalGridSupportFragment verticalGridSupportFragment4 = this.gridFragment;
        if (verticalGridSupportFragment4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("gridFragment");
            verticalGridSupportFragment4 = null;
        }
        verticalGridSupportFragment4.setOnItemViewClickedListener(new OnItemViewClickedListener() { // from class: com.stremio.tv.views.addons.AddonsFragment$$ExternalSyntheticLambda2
            @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
            public final void onItemClicked(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Row row) {
                AddonsFragment.onViewCreated$lambda$2(this.f$0, viewHolder, obj, viewHolder2, row);
            }
        });
        View viewFindViewById = view.findViewById(R.id.addons_picker);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        Picker picker = (Picker) viewFindViewById;
        this.picker = picker;
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
        picker3.setOnClickListener(new View.OnClickListener() { // from class: com.stremio.tv.views.addons.AddonsFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AddonsFragment.onViewCreated$lambda$3(this.f$0, view2);
            }
        });
        Picker picker4 = this.picker;
        if (picker4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("picker");
            picker4 = null;
        }
        picker4.addOnValueChangedListener(new Picker.PickerValueListener() { // from class: com.stremio.tv.views.addons.AddonsFragment$$ExternalSyntheticLambda4
            @Override // androidx.leanback.widget.picker.Picker.PickerValueListener
            public final void onValueChanged(Picker picker5, int i) {
                AddonsFragment.onViewCreated$lambda$4(this.f$0, picker5, i);
            }
        });
        Button button = (Button) view.findViewById(R.id.add_addon_button);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.stremio.tv.views.addons.AddonsFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.showAddAddonDialog();
            }
        });
        Intrinsics.checkNotNull(button);
        Drawable drawable = AppCompatResources.getDrawable(requireContext(), R.drawable.add);
        Intrinsics.checkNotNull(drawable);
        BindingUtils.setScaledDrawableStart(button, drawable, 0.8d, AppCompatResources.getColorStateList(requireContext(), R.color.accent_button_text_color));
        StateFlow<AddonsState> state = getViewModel().getState();
        Lifecycle lifecycle = getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "<get-lifecycle>(...)");
        Flow flowOnEach = FlowKt.onEach(FlowExtKt.flowWithLifecycle(state, lifecycle, Lifecycle.State.STARTED), new AnonymousClass7(view, this, null));
        AddonsFragment addonsFragment = this;
        FlowKt.launchIn(flowOnEach, LifecycleOwnerKt.getLifecycleScope(addonsFragment));
        StateFlow<AddonsState> state2 = getViewModel().getState();
        Lifecycle lifecycle2 = getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle2, "<get-lifecycle>(...)");
        FlowKt.launchIn(FlowKt.onEach(FlowKt.distinctUntilChanged(FlowExtKt.flowWithLifecycle(state2, lifecycle2, Lifecycle.State.STARTED), new Function2() { // from class: com.stremio.tv.views.addons.AddonsFragment$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(AddonsFragment.onViewCreated$lambda$6((AddonsState) obj, (AddonsState) obj2));
            }
        }), new AnonymousClass9(view, this, null)), LifecycleOwnerKt.getLifecycleScope(addonsFragment));
    }

    static final void onViewCreated$lambda$1(AddonsFragment addonsFragment, Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Row row) {
        Picker picker = null;
        DescriptorModel descriptorModel = obj instanceof DescriptorModel ? (DescriptorModel) obj : null;
        AddonDescriptor descriptor = descriptorModel != null ? descriptorModel.getDescriptor() : null;
        AddonsFragment$backPressedCallback$1 addonsFragment$backPressedCallback$1 = addonsFragment.backPressedCallback;
        Picker picker2 = addonsFragment.picker;
        if (picker2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("picker");
        } else {
            picker = picker2;
        }
        addonsFragment$backPressedCallback$1.setEnabled(picker.isActivated() || CollectionsKt.indexOf((List<? extends AddonDescriptor>) addonsFragment.getController().getRows(), descriptor) != 0);
    }

    static final void onViewCreated$lambda$2(AddonsFragment addonsFragment, Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Row row) {
        if (obj instanceof DescriptorModel) {
            addonsFragment.navigateToAddonDetails(((DescriptorModel) obj).getDescriptor().getTransportUrl());
        }
    }

    static final void onViewCreated$lambda$3(AddonsFragment addonsFragment, View view) {
        Picker picker = addonsFragment.picker;
        Picker picker2 = null;
        if (picker == null) {
            Intrinsics.throwUninitializedPropertyAccessException("picker");
            picker = null;
        }
        Picker picker3 = addonsFragment.picker;
        if (picker3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("picker");
            picker3 = null;
        }
        picker.setActivated(!picker3.isActivated());
        AddonsFragment$backPressedCallback$1 addonsFragment$backPressedCallback$1 = addonsFragment.backPressedCallback;
        Picker picker4 = addonsFragment.picker;
        if (picker4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("picker");
        } else {
            picker2 = picker4;
        }
        addonsFragment$backPressedCallback$1.setEnabled(picker2.isActivated());
        addonsFragment.updateFocus();
    }

    static final void onViewCreated$lambda$4(AddonsFragment addonsFragment, Picker picker, int i) {
        ResourceRequest request;
        Intrinsics.checkNotNullParameter(picker, "picker");
        AddonsState value = addonsFragment.getViewModel().getState().getValue();
        PickerColumn columnAt = picker.getColumnAt(i);
        int currentValue = columnAt != null ? columnAt.getCurrentValue() : 0;
        if (i == 0) {
            request = value.getCatalogs().get(currentValue).getRequest();
        } else {
            request = i != 1 ? null : value.getTypes().get(currentValue).getRequest();
        }
        if (request != null) {
            addonsFragment.getViewModel().loadAddons(request);
        }
    }

    /* JADX INFO: renamed from: com.stremio.tv.views.addons.AddonsFragment$onViewCreated$7, reason: invalid class name */
    /* JADX INFO: compiled from: AddonsFragment.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/stremio/common/viewmodels/state/AddonsState;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.tv.views.addons.AddonsFragment$onViewCreated$7", f = "AddonsFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass7 extends SuspendLambda implements Function2<AddonsState, Continuation<? super Unit>, Object> {
        final /* synthetic */ View $view;
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ AddonsFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass7(View view, AddonsFragment addonsFragment, Continuation<? super AnonymousClass7> continuation) {
            super(2, continuation);
            this.$view = view;
            this.this$0 = addonsFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass7 anonymousClass7 = new AnonymousClass7(this.$view, this.this$0, continuation);
            anonymousClass7.L$0 = obj;
            return anonymousClass7;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AddonsState addonsState, Continuation<? super Unit> continuation) {
            return ((AnonymousClass7) create(addonsState, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            final AddonsState addonsState = (AddonsState) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            View view = this.$view;
            final AddonsFragment addonsFragment = this.this$0;
            view.post(new Runnable() { // from class: com.stremio.tv.views.addons.AddonsFragment$onViewCreated$7$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    addonsFragment.updateAddonCatalog(addonsState.getCatalog());
                }
            });
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.stremio.tv.views.addons.AddonsFragment$onViewCreated$9, reason: invalid class name */
    /* JADX INFO: compiled from: AddonsFragment.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/stremio/common/viewmodels/state/AddonsState;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.tv.views.addons.AddonsFragment$onViewCreated$9", f = "AddonsFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass9 extends SuspendLambda implements Function2<AddonsState, Continuation<? super Unit>, Object> {
        final /* synthetic */ View $view;
        int label;
        final /* synthetic */ AddonsFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass9(View view, AddonsFragment addonsFragment, Continuation<? super AnonymousClass9> continuation) {
            super(2, continuation);
            this.$view = view;
            this.this$0 = addonsFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass9(this.$view, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AddonsState addonsState, Continuation<? super Unit> continuation) {
            return ((AnonymousClass9) create(addonsState, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            View view = this.$view;
            final AddonsFragment addonsFragment = this.this$0;
            view.post(new Runnable() { // from class: com.stremio.tv.views.addons.AddonsFragment$onViewCreated$9$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AddonsFragment addonsFragment2 = addonsFragment;
                    addonsFragment2.populatePickerColumns(addonsFragment2.getViewModel().getState().getValue());
                }
            });
            return Unit.INSTANCE;
        }
    }

    static final boolean onViewCreated$lambda$6(AddonsState old, AddonsState addonsState) {
        Intrinsics.checkNotNullParameter(old, "old");
        Intrinsics.checkNotNullParameter(addonsState, "new");
        return Intrinsics.areEqual(old.getTypes(), addonsState.getTypes()) && Intrinsics.areEqual(old.getCatalogs(), addonsState.getCatalogs());
    }

    @Override // com.stremio.tv.views.FocusableFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        updateFocus();
        View view = getView();
        if (view != null) {
            view.post(new Runnable() { // from class: com.stremio.tv.views.addons.AddonsFragment$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AddonsFragment.onResume$lambda$0(this.f$0);
                }
            });
        }
    }

    static final void onResume$lambda$0(AddonsFragment addonsFragment) {
        BrowseFrameLayout browseFrameLayout;
        View view = addonsFragment.getView();
        if (view == null || (browseFrameLayout = (BrowseFrameLayout) view.findViewById(R.id.grid_frame)) == null) {
            return;
        }
        browseFrameLayout.setOnFocusSearchListener(null);
    }

    private final void updateFocus() {
        Picker picker = this.picker;
        VerticalGridSupportFragment verticalGridSupportFragment = null;
        if (picker == null) {
            Intrinsics.throwUninitializedPropertyAccessException("picker");
            picker = null;
        }
        if (picker.isActivated()) {
            return;
        }
        VerticalGridSupportFragment verticalGridSupportFragment2 = this.gridFragment;
        if (verticalGridSupportFragment2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("gridFragment");
        } else {
            verticalGridSupportFragment = verticalGridSupportFragment2;
        }
        View view = verticalGridSupportFragment.getView();
        if (view != null) {
            view.requestFocus();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void navigateToAddonDetails(String transportUrl) {
        if (transportUrl.length() == 0) {
            return;
        }
        FragmentKt.findNavController(this).navigate(AddonsWrapperFragmentDirections.INSTANCE.actionToAddonDetails(transportUrl));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showAddAddonDialog() {
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        TextInputLayout textInputLayout = new TextInputLayout(contextRequireContext);
        TextInputLayout textInputLayout2 = textInputLayout;
        int dimensionPixelSize = textInputLayout.getResources().getDimensionPixelSize(R.dimen.dialog_large_padding);
        textInputLayout2.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        final EditText editText = new EditText(contextRequireContext);
        textInputLayout.addView(editText);
        new AlertDialog.Builder(contextRequireContext, R.style.CustomAlertDialog).setView(textInputLayout2).setTitle(getString(R.string.label_add_addon)).setMessage(getString(R.string.label_add_addon_description)).setNegativeButton(getString(R.string.label_button_cancel), (DialogInterface.OnClickListener) null).setPositiveButton(getString(R.string.label_addon_add), new DialogInterface.OnClickListener() { // from class: com.stremio.tv.views.addons.AddonsFragment$$ExternalSyntheticLambda8
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.navigateToAddonDetails(editText.getText().toString());
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateAddonCatalog(LoadableAddonCatalog catalog) {
        int iSwitchDispatch = AddonsFragment$$ExternalSyntheticTypeSwitchClass9.switchDispatch(catalog != null ? catalog.getContent() : null, 0);
        if (iSwitchDispatch == 0) {
            getController().setMessage(getString(R.string.label_stremio_tv_loading));
        } else if (iSwitchDispatch == 1) {
            getController().setMessage(LoadableExtKt.getAsErrorMessage(catalog));
        } else {
            getController().setMessage(null);
            getController().setRows(LoadableExtKt.getAsReady(catalog));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void populatePickerColumns(AddonsState state) {
        int i;
        List<AddonsWithFilters.SelectableCatalog> catalogs = state.getCatalogs();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(catalogs, 10));
        Iterator<T> it = catalogs.iterator();
        while (it.hasNext()) {
            arrayList.add(toDisplayCatalogName(((AddonsWithFilters.SelectableCatalog) it.next()).getName()));
        }
        int i2 = 0;
        String[] strArr = (String[]) arrayList.toArray(new String[0]);
        Iterator<AddonsWithFilters.SelectableCatalog> it2 = state.getCatalogs().iterator();
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
        List<AddonsWithFilters.SelectableType> types = state.getTypes();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(types, 10));
        Iterator<T> it3 = types.iterator();
        while (it3.hasNext()) {
            arrayList2.add(ContextExtKt.toDisplayMetaType(((AddonsWithFilters.SelectableType) it3.next()).getType(), getContext()));
        }
        String[] strArr2 = (String[]) arrayList2.toArray(new String[0]);
        Iterator<AddonsWithFilters.SelectableType> it4 = state.getTypes().iterator();
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

    private final String toDisplayCatalogName(String name) {
        Context context;
        Context context2;
        Context context3;
        int iHashCode = name.hashCode();
        String string = null;
        if (iHashCode != -700645621) {
            if (iHashCode != 523718601) {
                if (iHashCode == 2033014618 && name.equals("Installed") && (context3 = getContext()) != null) {
                    string = context3.getString(R.string.label_addon_installed);
                }
            } else if (name.equals("Community") && (context2 = getContext()) != null) {
                string = context2.getString(R.string.label_addon_community);
            }
        } else if (name.equals("Official") && (context = getContext()) != null) {
            string = context.getString(R.string.label_addon_official);
        }
        return string == null ? name : string;
    }
}