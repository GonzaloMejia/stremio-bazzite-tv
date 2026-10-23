package com.stremio.tv.views.metadetails;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.FlowExtKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.NavArgsLazy;
import androidx.navigation.NavController;
import androidx.navigation.fragment.FragmentKt;
import com.stremio.common.viewmodels.MetaDetailsViewModel;
import com.stremio.common.viewmodels.state.MetaDetailsEvent;
import com.stremio.common.viewmodels.state.MetaDetailsState;
import com.stremio.core.types.resource.MetaItem;
import com.stremio.core.types.resource.Stream;
import com.stremio.core.types.resource.Video;
import com.stremio.tv.R;
import com.stremio.tv.databinding.FragmentMetaDetailsBinding;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
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
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.core.qualifier.Qualifier;
import org.koin.viewmodel.GetViewModelKt;
import stremio.core.types.Rating;
import stremio.core.types.RatingInfo;

/* JADX INFO: compiled from: MetaDetailsFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000m\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000*\u0001\u0015\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u001a\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001e\u001a\u00020\u0018H\u0016J\u0010\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010\"\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!H\u0002J\u001e\u0010#\u001a\u00020\u00182\u0006\u0010$\u001a\u00020\u001d2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&H\u0002J \u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,H\u0002J\u0010\u0010.\u001a\u00020'2\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010/\u001a\u00020'2\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u00100\u001a\u00020'2\u0006\u0010 \u001a\u00020!H\u0002J\b\u00101\u001a\u00020\u0018H\u0002J\u001e\u00102\u001a\u00020\u00182\u0006\u0010$\u001a\u00020\u001d2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&H\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0016Ê\u0001\f\b4\u0012\b\b5\u0012\u0004\b\u0003\u0010\u0000¨\u00063"}, d2 = {"Lcom/stremio/tv/views/metadetails/MetaDetailsFragment;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "args", "Lcom/stremio/tv/views/metadetails/MetaDetailsFragmentArgs;", "getArgs", "()Lcom/stremio/tv/views/metadetails/MetaDetailsFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "viewModel", "Lcom/stremio/common/viewmodels/MetaDetailsViewModel;", "getViewModel", "()Lcom/stremio/common/viewmodels/MetaDetailsViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "binding", "Lcom/stremio/tv/databinding/FragmentMetaDetailsBinding;", "tooltipHelper", "Lcom/stremio/tv/views/metadetails/TooltipHelper;", "backPressedCallback", "com/stremio/tv/views/metadetails/MetaDetailsFragment$backPressedCallback$1", "Lcom/stremio/tv/views/metadetails/MetaDetailsFragment$backPressedCallback$1;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "Landroid/view/View;", "onStop", "onStateUpdate", "state", "Lcom/stremio/common/viewmodels/state/MetaDetailsState;", "updateTooltipsForState", "updateTooltipIfFocused", "button", "textProvider", "Lkotlin/Function0;", "", "getToggleText", "condition", "", "trueTextResId", "", "falseTextResId", "getLibraryButtonText", "getWatchedButtonText", "getNotificationsButtonText", "setupButtonTooltips", "setupTooltip", "Stremio:androidTV-com.stremio.one-1.11.2-40000002_release", "Landroidx/compose/runtime/internal/StabilityInferred;", "parameters"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class MetaDetailsFragment extends Fragment {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: args$delegate, reason: from kotlin metadata */
    private final NavArgsLazy args;
    private final MetaDetailsFragment$backPressedCallback$1 backPressedCallback;
    private FragmentMetaDetailsBinding binding;
    private TooltipHelper tooltipHelper;

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Type inference failed for: r0v5, types: [com.stremio.tv.views.metadetails.MetaDetailsFragment$backPressedCallback$1] */
    public MetaDetailsFragment() {
        super(R.layout.fragment_meta_details);
        final MetaDetailsFragment metaDetailsFragment = this;
        this.args = new NavArgsLazy(Reflection.getOrCreateKotlinClass(MetaDetailsFragmentArgs.class), new Function0<Bundle>() { // from class: com.stremio.tv.views.metadetails.MetaDetailsFragment$special$$inlined$navArgs$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Bundle invoke() {
                Bundle arguments = metaDetailsFragment.getArguments();
                if (arguments != null) {
                    return arguments;
                }
                throw new IllegalStateException("Fragment " + metaDetailsFragment + " has null arguments");
            }
        });
        final Function0<ViewModelStoreOwner> function0 = new Function0<ViewModelStoreOwner>() { // from class: com.stremio.tv.views.metadetails.MetaDetailsFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return metaDetailsFragment;
            }
        };
        final Qualifier qualifier = null;
        final Function0 function1 = null;
        final Function0 function2 = null;
        this.viewModel = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<MetaDetailsViewModel>() { // from class: com.stremio.tv.views.metadetails.MetaDetailsFragment$special$$inlined$viewModel$default$2
            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r10v4, types: [androidx.lifecycle.ViewModel, com.stremio.common.viewmodels.MetaDetailsViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final MetaDetailsViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = metaDetailsFragment;
                Qualifier qualifier2 = qualifier;
                Function0 function3 = function0;
                Function0 function4 = function1;
                Function0 function5 = function2;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function3.invoke()).getViewModelStore();
                if (function4 == null || (defaultViewModelCreationExtras = (CreationExtras) function4.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "<get-defaultViewModelCreationExtras>(...)");
                }
                return GetViewModelKt.resolveViewModel$default(Reflection.getOrCreateKotlinClass(MetaDetailsViewModel.class), viewModelStore, null, defaultViewModelCreationExtras, qualifier2, AndroidKoinScopeExtKt.getKoinScope(fragment), function5, 4, null);
            }
        });
        this.backPressedCallback = new OnBackPressedCallback() { // from class: com.stremio.tv.views.metadetails.MetaDetailsFragment$backPressedCallback$1
            {
                super(false);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                this.this$0.getViewModel().onEvent(MetaDetailsEvent.ClearStreams.INSTANCE);
                FragmentMetaDetailsBinding fragmentMetaDetailsBinding = this.this$0.binding;
                if (fragmentMetaDetailsBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentMetaDetailsBinding = null;
                }
                fragmentMetaDetailsBinding.metaDetailsVideos.requestFocus();
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final MetaDetailsFragmentArgs getArgs() {
        return (MetaDetailsFragmentArgs) this.args.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MetaDetailsViewModel getViewModel() {
        return (MetaDetailsViewModel) this.viewModel.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().onEvent(new MetaDetailsEvent.LoadMetaItem(getArgs().getType(), getArgs().getId(), getArgs().getVideoId(), getArgs().getAutoPlay()));
        requireActivity().getOnBackPressedDispatcher().addCallback(this, this.backPressedCallback);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        FragmentMetaDetailsBinding fragmentMetaDetailsBindingBind = FragmentMetaDetailsBinding.bind(view);
        Intrinsics.checkNotNullExpressionValue(fragmentMetaDetailsBindingBind, "bind(...)");
        this.binding = fragmentMetaDetailsBindingBind;
        if (fragmentMetaDetailsBindingBind == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMetaDetailsBindingBind = null;
        }
        fragmentMetaDetailsBindingBind.setViewModel(getViewModel());
        FragmentMetaDetailsBinding fragmentMetaDetailsBinding = this.binding;
        if (fragmentMetaDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMetaDetailsBinding = null;
        }
        fragmentMetaDetailsBinding.setLifecycleOwner(getViewLifecycleOwner());
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        View rootView = view.getRootView();
        Intrinsics.checkNotNull(rootView, "null cannot be cast to non-null type android.view.ViewGroup");
        this.tooltipHelper = new TooltipHelper(contextRequireContext, (ViewGroup) rootView);
        FragmentMetaDetailsBinding fragmentMetaDetailsBinding2 = this.binding;
        if (fragmentMetaDetailsBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMetaDetailsBinding2 = null;
        }
        fragmentMetaDetailsBinding2.metaDetailBtnTrailer.setOnClickListener(new View.OnClickListener() { // from class: com.stremio.tv.views.metadetails.MetaDetailsFragment$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                MetaDetailsFragment.onViewCreated$lambda$0(this.f$0, view2);
            }
        });
        FragmentMetaDetailsBinding fragmentMetaDetailsBinding3 = this.binding;
        if (fragmentMetaDetailsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMetaDetailsBinding3 = null;
        }
        fragmentMetaDetailsBinding3.metaDetailBtnLibrary.setOnClickListener(new View.OnClickListener() { // from class: com.stremio.tv.views.metadetails.MetaDetailsFragment$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.getViewModel().onEvent(MetaDetailsEvent.ToggleInLibrary.INSTANCE);
            }
        });
        FragmentMetaDetailsBinding fragmentMetaDetailsBinding4 = this.binding;
        if (fragmentMetaDetailsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMetaDetailsBinding4 = null;
        }
        fragmentMetaDetailsBinding4.metaDetailBtnWatched.setOnClickListener(new View.OnClickListener() { // from class: com.stremio.tv.views.metadetails.MetaDetailsFragment$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.getViewModel().onEvent(MetaDetailsEvent.ToggleWatched.INSTANCE);
            }
        });
        FragmentMetaDetailsBinding fragmentMetaDetailsBinding5 = this.binding;
        if (fragmentMetaDetailsBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMetaDetailsBinding5 = null;
        }
        fragmentMetaDetailsBinding5.metaDetailBtnToggleNotifications.setOnClickListener(new View.OnClickListener() { // from class: com.stremio.tv.views.metadetails.MetaDetailsFragment$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.getViewModel().onEvent(MetaDetailsEvent.ToggleNotifications.INSTANCE);
            }
        });
        FragmentMetaDetailsBinding fragmentMetaDetailsBinding6 = this.binding;
        if (fragmentMetaDetailsBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMetaDetailsBinding6 = null;
        }
        fragmentMetaDetailsBinding6.metaDetailBtnRateLike.setOnClickListener(new View.OnClickListener() { // from class: com.stremio.tv.views.metadetails.MetaDetailsFragment$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                MetaDetailsFragment.onViewCreated$lambda$4(this.f$0, view2);
            }
        });
        FragmentMetaDetailsBinding fragmentMetaDetailsBinding7 = this.binding;
        if (fragmentMetaDetailsBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMetaDetailsBinding7 = null;
        }
        fragmentMetaDetailsBinding7.metaDetailBtnRateLove.setOnClickListener(new View.OnClickListener() { // from class: com.stremio.tv.views.metadetails.MetaDetailsFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                MetaDetailsFragment.onViewCreated$lambda$5(this.f$0, view2);
            }
        });
        setupButtonTooltips();
        StateFlow<MetaDetailsState> state = getViewModel().getState();
        Lifecycle lifecycle = getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "<get-lifecycle>(...)");
        Flow flowOnEach = FlowKt.onEach(FlowExtKt.flowWithLifecycle(state, lifecycle, Lifecycle.State.STARTED), new AnonymousClass7(null));
        MetaDetailsFragment metaDetailsFragment = this;
        FlowKt.launchIn(flowOnEach, LifecycleOwnerKt.getLifecycleScope(metaDetailsFragment));
        StateFlow<MetaDetailsState> state2 = getViewModel().getState();
        Lifecycle lifecycle2 = getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle2, "<get-lifecycle>(...)");
        FlowKt.launchIn(FlowExtKt.flowWithLifecycle(state2, lifecycle2, Lifecycle.State.STARTED), LifecycleOwnerKt.getLifecycleScope(metaDetailsFragment));
    }

    static final void onViewCreated$lambda$0(MetaDetailsFragment metaDetailsFragment, View view) {
        Stream trailerStream = metaDetailsFragment.getViewModel().getState().getValue().getTrailerStream();
        if (trailerStream != null) {
            NavController navControllerFindNavController = FragmentKt.findNavController(metaDetailsFragment);
            Uri uri = Uri.parse(trailerStream.getDeepLinks().getPlayer());
            Intrinsics.checkNotNullExpressionValue(uri, "parse(...)");
            navControllerFindNavController.navigate(uri);
        }
    }

    static final void onViewCreated$lambda$4(MetaDetailsFragment metaDetailsFragment, View view) {
        RatingInfo ratingInfo = metaDetailsFragment.getViewModel().getState().getValue().getRatingInfo();
        metaDetailsFragment.getViewModel().onEvent(new MetaDetailsEvent.Rate(Intrinsics.areEqual(ratingInfo != null ? ratingInfo.getStatus() : null, Rating.LIKED.INSTANCE) ? null : Rating.LIKED.INSTANCE));
    }

    static final void onViewCreated$lambda$5(MetaDetailsFragment metaDetailsFragment, View view) {
        RatingInfo ratingInfo = metaDetailsFragment.getViewModel().getState().getValue().getRatingInfo();
        metaDetailsFragment.getViewModel().onEvent(new MetaDetailsEvent.Rate(Intrinsics.areEqual(ratingInfo != null ? ratingInfo.getStatus() : null, Rating.LOVED.INSTANCE) ? null : Rating.LOVED.INSTANCE));
    }

    /* JADX INFO: renamed from: com.stremio.tv.views.metadetails.MetaDetailsFragment$onViewCreated$7, reason: invalid class name */
    /* JADX INFO: compiled from: MetaDetailsFragment.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/stremio/common/viewmodels/state/MetaDetailsState;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.tv.views.metadetails.MetaDetailsFragment$onViewCreated$7", f = "MetaDetailsFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass7 extends SuspendLambda implements Function2<MetaDetailsState, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass7(Continuation<? super AnonymousClass7> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass7 anonymousClass7 = MetaDetailsFragment.this.new AnonymousClass7(continuation);
            anonymousClass7.L$0 = obj;
            return anonymousClass7;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(MetaDetailsState metaDetailsState, Continuation<? super Unit> continuation) {
            return ((AnonymousClass7) create(metaDetailsState, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            MetaDetailsState metaDetailsState = (MetaDetailsState) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            MetaDetailsFragment.this.onStateUpdate(metaDetailsState);
            return Unit.INSTANCE;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        getViewModel().onEvent(MetaDetailsEvent.ClearWaitAutoPlay.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final void onStateUpdate(MetaDetailsState state) {
        boolean z;
        MetaDetailsFragment$backPressedCallback$1 metaDetailsFragment$backPressedCallback$1 = this.backPressedCallback;
        if (state.getStreamListVisible()) {
            z = state.getVideos().size() > 1;
        }
        metaDetailsFragment$backPressedCallback$1.setEnabled(z);
        updateTooltipsForState(state);
    }

    private final void updateTooltipsForState(final MetaDetailsState state) {
        FragmentMetaDetailsBinding fragmentMetaDetailsBinding = this.binding;
        FragmentMetaDetailsBinding fragmentMetaDetailsBinding2 = null;
        if (fragmentMetaDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMetaDetailsBinding = null;
        }
        Button metaDetailBtnLibrary = fragmentMetaDetailsBinding.metaDetailBtnLibrary;
        Intrinsics.checkNotNullExpressionValue(metaDetailBtnLibrary, "metaDetailBtnLibrary");
        updateTooltipIfFocused(metaDetailBtnLibrary, new Function0() { // from class: com.stremio.tv.views.metadetails.MetaDetailsFragment$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.getLibraryButtonText(state);
            }
        });
        FragmentMetaDetailsBinding fragmentMetaDetailsBinding3 = this.binding;
        if (fragmentMetaDetailsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMetaDetailsBinding3 = null;
        }
        Button metaDetailBtnWatched = fragmentMetaDetailsBinding3.metaDetailBtnWatched;
        Intrinsics.checkNotNullExpressionValue(metaDetailBtnWatched, "metaDetailBtnWatched");
        updateTooltipIfFocused(metaDetailBtnWatched, new Function0() { // from class: com.stremio.tv.views.metadetails.MetaDetailsFragment$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.getWatchedButtonText(state);
            }
        });
        FragmentMetaDetailsBinding fragmentMetaDetailsBinding4 = this.binding;
        if (fragmentMetaDetailsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentMetaDetailsBinding2 = fragmentMetaDetailsBinding4;
        }
        Button metaDetailBtnToggleNotifications = fragmentMetaDetailsBinding2.metaDetailBtnToggleNotifications;
        Intrinsics.checkNotNullExpressionValue(metaDetailBtnToggleNotifications, "metaDetailBtnToggleNotifications");
        updateTooltipIfFocused(metaDetailBtnToggleNotifications, new Function0() { // from class: com.stremio.tv.views.metadetails.MetaDetailsFragment$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.getNotificationsButtonText(state);
            }
        });
    }

    private final void updateTooltipIfFocused(View button, Function0<String> textProvider) {
        if (button.hasFocus()) {
            TooltipHelper tooltipHelper = this.tooltipHelper;
            if (tooltipHelper == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tooltipHelper");
                tooltipHelper = null;
            }
            tooltipHelper.updateTooltip(button, textProvider.invoke());
        }
    }

    private final String getToggleText(boolean condition, int trueTextResId, int falseTextResId) {
        if (!condition) {
            trueTextResId = falseTextResId;
        }
        String string = getString(trueTextResId);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getLibraryButtonText(MetaDetailsState state) {
        MetaItem meta = state.getMeta();
        boolean z = false;
        if (meta != null && meta.getInLibrary()) {
            z = true;
        }
        return getToggleText(z, R.string.label_remove_from_lib, R.string.label_add_to_lib);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getWatchedButtonText(MetaDetailsState state) {
        Video video = state.getVideo();
        boolean z = false;
        if (video != null && video.getWatched()) {
            z = true;
        }
        return getToggleText(z, R.string.label_ctx_mark_unwatched, R.string.label_ctx_mark_watched);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getNotificationsButtonText(MetaDetailsState state) {
        MetaItem meta = state.getMeta();
        boolean z = false;
        if (meta != null && meta.getReceiveNotifications()) {
            z = true;
        }
        return getToggleText(z, R.string.label_ctx_disable_new_notif, R.string.label_ctx_enable_new_notif);
    }

    private final void setupButtonTooltips() {
        FragmentMetaDetailsBinding fragmentMetaDetailsBinding = this.binding;
        FragmentMetaDetailsBinding fragmentMetaDetailsBinding2 = null;
        if (fragmentMetaDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMetaDetailsBinding = null;
        }
        Button metaDetailBtnTrailer = fragmentMetaDetailsBinding.metaDetailBtnTrailer;
        Intrinsics.checkNotNullExpressionValue(metaDetailBtnTrailer, "metaDetailBtnTrailer");
        setupTooltip(metaDetailBtnTrailer, new Function0() { // from class: com.stremio.tv.views.metadetails.MetaDetailsFragment$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MetaDetailsFragment.setupButtonTooltips$lambda$0(this.f$0);
            }
        });
        FragmentMetaDetailsBinding fragmentMetaDetailsBinding3 = this.binding;
        if (fragmentMetaDetailsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMetaDetailsBinding3 = null;
        }
        Button metaDetailBtnLibrary = fragmentMetaDetailsBinding3.metaDetailBtnLibrary;
        Intrinsics.checkNotNullExpressionValue(metaDetailBtnLibrary, "metaDetailBtnLibrary");
        setupTooltip(metaDetailBtnLibrary, new Function0() { // from class: com.stremio.tv.views.metadetails.MetaDetailsFragment$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                MetaDetailsFragment metaDetailsFragment = this.f$0;
                return metaDetailsFragment.getLibraryButtonText(metaDetailsFragment.getViewModel().getState().getValue());
            }
        });
        FragmentMetaDetailsBinding fragmentMetaDetailsBinding4 = this.binding;
        if (fragmentMetaDetailsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMetaDetailsBinding4 = null;
        }
        Button metaDetailBtnWatched = fragmentMetaDetailsBinding4.metaDetailBtnWatched;
        Intrinsics.checkNotNullExpressionValue(metaDetailBtnWatched, "metaDetailBtnWatched");
        setupTooltip(metaDetailBtnWatched, new Function0() { // from class: com.stremio.tv.views.metadetails.MetaDetailsFragment$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                MetaDetailsFragment metaDetailsFragment = this.f$0;
                return metaDetailsFragment.getWatchedButtonText(metaDetailsFragment.getViewModel().getState().getValue());
            }
        });
        FragmentMetaDetailsBinding fragmentMetaDetailsBinding5 = this.binding;
        if (fragmentMetaDetailsBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentMetaDetailsBinding2 = fragmentMetaDetailsBinding5;
        }
        Button metaDetailBtnToggleNotifications = fragmentMetaDetailsBinding2.metaDetailBtnToggleNotifications;
        Intrinsics.checkNotNullExpressionValue(metaDetailBtnToggleNotifications, "metaDetailBtnToggleNotifications");
        setupTooltip(metaDetailBtnToggleNotifications, new Function0() { // from class: com.stremio.tv.views.metadetails.MetaDetailsFragment$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                MetaDetailsFragment metaDetailsFragment = this.f$0;
                return metaDetailsFragment.getNotificationsButtonText(metaDetailsFragment.getViewModel().getState().getValue());
            }
        });
    }

    static final String setupButtonTooltips$lambda$0(MetaDetailsFragment metaDetailsFragment) {
        String string = metaDetailsFragment.getString(R.string.label_trailer);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    private final void setupTooltip(final View button, final Function0<String> textProvider) {
        button.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.stremio.tv.views.metadetails.MetaDetailsFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                MetaDetailsFragment.setupTooltip$lambda$0(this.f$0, button, textProvider, view, z);
            }
        });
    }

    static final void setupTooltip$lambda$0(MetaDetailsFragment metaDetailsFragment, View view, Function0 function0, View view2, boolean z) {
        TooltipHelper tooltipHelper = null;
        if (z) {
            TooltipHelper tooltipHelper2 = metaDetailsFragment.tooltipHelper;
            if (tooltipHelper2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tooltipHelper");
            } else {
                tooltipHelper = tooltipHelper2;
            }
            tooltipHelper.showTooltip(view, (String) function0.invoke());
            return;
        }
        TooltipHelper tooltipHelper3 = metaDetailsFragment.tooltipHelper;
        if (tooltipHelper3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tooltipHelper");
        } else {
            tooltipHelper = tooltipHelper3;
        }
        tooltipHelper.hideTooltip(view);
    }
}