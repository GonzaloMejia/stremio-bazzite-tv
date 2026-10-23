package com.stremio.tv.views.metadetails.streams;

import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import androidx.fragment.app.Fragment;
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
import com.stremio.common.viewmodels.MetaDetailsViewModel;
import com.stremio.common.viewmodels.state.MetaDetailsState;
import com.stremio.core.types.resource.Stream;
import com.stremio.tv.R;
import com.stremio.tv.util.LoggingUtil;
import com.stremio.tv.views.metadetails.RowSupportFragment;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
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
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.viewmodel.GetViewModelKt;

/* JADX INFO: compiled from: StreamsListFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u001a\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0019\u001a\u00020\u0013H\u0016J\u0010\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u0013H\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000Ê\u0001\f\b\u001f\u0012\b\b \u0012\u0004\b\u0003\u0010\u0000¨\u0006\u001e"}, d2 = {"Lcom/stremio/tv/views/metadetails/streams/StreamsListFragment;", "Lcom/stremio/tv/views/metadetails/RowSupportFragment;", "<init>", "()V", "viewModel", "Lcom/stremio/common/viewmodels/MetaDetailsViewModel;", "getViewModel", "()Lcom/stremio/common/viewmodels/MetaDetailsViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "controller", "Lcom/stremio/tv/views/metadetails/streams/StreamsController;", "getController", "()Lcom/stremio/tv/views/metadetails/streams/StreamsController;", "controller$delegate", "isExpanded", "", "isAutoPlayingStream", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "Landroid/view/View;", "onResume", "navigateToStream", "stream", "Lcom/stremio/core/types/resource/Stream;", "requestFocus", "Stremio:androidTV-com.stremio.one-1.11.2-40000002_release", "Landroidx/compose/runtime/internal/StabilityInferred;", "parameters"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class StreamsListFragment extends RowSupportFragment {
    public static final int $stable = 8;
    private boolean isAutoPlayingStream;
    private boolean isExpanded;

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel = LazyKt.lazy(new Function0() { // from class: com.stremio.tv.views.metadetails.streams.StreamsListFragment$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return StreamsListFragment.viewModel_delegate$lambda$0(this.f$0);
        }
    });

    /* JADX INFO: renamed from: controller$delegate, reason: from kotlin metadata */
    private final Lazy controller = LazyKt.lazy(new Function0() { // from class: com.stremio.tv.views.metadetails.streams.StreamsListFragment$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return StreamsListFragment.controller_delegate$lambda$0(this.f$0);
        }
    });

    private final MetaDetailsViewModel getViewModel() {
        return (MetaDetailsViewModel) this.viewModel.getValue();
    }

    static final MetaDetailsViewModel viewModel_delegate$lambda$0(StreamsListFragment streamsListFragment) {
        final Fragment fragmentRequireParentFragment = streamsListFragment.requireParentFragment();
        Intrinsics.checkNotNullExpressionValue(fragmentRequireParentFragment, "requireParentFragment(...)");
        ViewModelStore viewModelStore = new Function0<ViewModelStoreOwner>() { // from class: com.stremio.tv.views.metadetails.streams.StreamsListFragment$viewModel_delegate$lambda$0$$inlined$getViewModel$default$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return fragmentRequireParentFragment;
            }
        }.invoke().getViewModelStore();
        CreationExtras defaultViewModelCreationExtras = fragmentRequireParentFragment.getDefaultViewModelCreationExtras();
        Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "<get-defaultViewModelCreationExtras>(...)");
        return (MetaDetailsViewModel) GetViewModelKt.resolveViewModel$default(Reflection.getOrCreateKotlinClass(MetaDetailsViewModel.class), viewModelStore, null, defaultViewModelCreationExtras, null, AndroidKoinScopeExtKt.getKoinScope(fragmentRequireParentFragment), null, 4, null);
    }

    static final StreamsController controller_delegate$lambda$0(StreamsListFragment streamsListFragment) {
        return new StreamsController(streamsListFragment.isExpanded);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final StreamsController getController() {
        return (StreamsController) this.controller.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypedValue typedValue = new TypedValue();
        requireContext().getTheme().resolveAttribute(R.attr.streamCardExpandedCardVisible, typedValue, true);
        boolean z = Boolean.parseBoolean(typedValue.coerceToString().toString());
        this.isExpanded = z;
        setExpand(z);
        setAdapter(getController().getAdapter());
        setOnItemViewSelectedListener(new BaseOnItemViewSelectedListener() { // from class: com.stremio.tv.views.metadetails.streams.StreamsListFragment$$ExternalSyntheticLambda0
            @Override // androidx.leanback.widget.BaseOnItemViewSelectedListener
            public final void onItemSelected(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Object obj2) {
                StreamsListFragment.onCreate$lambda$0(this.f$0, viewHolder, obj, viewHolder2, obj2);
            }
        });
        setOnItemViewClickedListener(new BaseOnItemViewClickedListener() { // from class: com.stremio.tv.views.metadetails.streams.StreamsListFragment$$ExternalSyntheticLambda1
            @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
            public final void onItemClicked(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Object obj2) {
                StreamsListFragment.onCreate$lambda$1(this.f$0, viewHolder, obj, viewHolder2, obj2);
            }
        });
        StateFlow<MetaDetailsState> state = getViewModel().getState();
        Lifecycle lifecycle = getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "<get-lifecycle>(...)");
        Flow flowOnEach = FlowKt.onEach(FlowKt.debounce(FlowExtKt.flowWithLifecycle(state, lifecycle, Lifecycle.State.STARTED), 100L), new AnonymousClass3(null));
        StreamsListFragment streamsListFragment = this;
        FlowKt.launchIn(flowOnEach, LifecycleOwnerKt.getLifecycleScope(streamsListFragment));
        StateFlow<Stream> autoPlayStream = getViewModel().getAutoPlayStream();
        Lifecycle lifecycle2 = getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle2, "<get-lifecycle>(...)");
        FlowKt.launchIn(FlowKt.onEach(FlowKt.filterNotNull(FlowExtKt.flowWithLifecycle(autoPlayStream, lifecycle2, Lifecycle.State.CREATED)), new AnonymousClass4(null)), LifecycleOwnerKt.getLifecycleScope(streamsListFragment));
    }

    static final void onCreate$lambda$0(StreamsListFragment streamsListFragment, Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Object obj2) {
        if (obj instanceof StreamModel) {
            streamsListFragment.getController().setSelectedStream(((StreamModel) obj).getStream());
        }
    }

    static final void onCreate$lambda$1(StreamsListFragment streamsListFragment, Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Object obj2) {
        if (obj instanceof StreamModel) {
            streamsListFragment.navigateToStream(((StreamModel) obj).getStream());
        }
    }

    /* JADX INFO: renamed from: com.stremio.tv.views.metadetails.streams.StreamsListFragment$onCreate$3, reason: invalid class name */
    /* JADX INFO: compiled from: StreamsListFragment.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "state", "Lcom/stremio/common/viewmodels/state/MetaDetailsState;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.tv.views.metadetails.streams.StreamsListFragment$onCreate$3", f = "StreamsListFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass3 extends SuspendLambda implements Function2<MetaDetailsState, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 anonymousClass3 = StreamsListFragment.this.new AnonymousClass3(continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(MetaDetailsState metaDetailsState, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(metaDetailsState, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object next;
            MetaDetailsState metaDetailsState = (MetaDetailsState) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (StreamsListFragment.this.getController().getItems().isEmpty() && !metaDetailsState.getStreams().isEmpty()) {
                    StreamsListFragment.this.requestFocus();
                }
                Stream selectedItem = StreamsListFragment.this.getController().getSelectedItem();
                boolean z = CollectionsKt.indexOf((List<? extends Stream>) StreamsListFragment.this.getController().getItems(), selectedItem) <= 0;
                if (z) {
                    Iterator<T> it = metaDetailsState.getStreams().iterator();
                    do {
                        if (!it.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it.next();
                    } while (!Intrinsics.areEqual((Stream) next, metaDetailsState.getSuggestedStream()));
                    selectedItem = (Stream) next;
                } else if (z) {
                    throw new NoWhenBranchMatchedException();
                }
                if (selectedItem == null) {
                    selectedItem = (Stream) CollectionsKt.firstOrNull((List) metaDetailsState.getStreams());
                }
                int iCoerceAtLeast = RangesKt.coerceAtLeast(CollectionsKt.indexOf((List<? extends Stream>) metaDetailsState.getStreams(), selectedItem), 0);
                StreamsListFragment.this.getController().setSelectedItem(selectedItem);
                StreamsListFragment.this.getController().setItems(metaDetailsState.getStreams());
                StreamsListFragment.this.scrollToIndex(iCoerceAtLeast);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: renamed from: com.stremio.tv.views.metadetails.streams.StreamsListFragment$onCreate$4, reason: invalid class name */
    /* JADX INFO: compiled from: StreamsListFragment.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/stremio/core/types/resource/Stream;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.tv.views.metadetails.streams.StreamsListFragment$onCreate$4", f = "StreamsListFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass4 extends SuspendLambda implements Function2<Stream, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass4 anonymousClass4 = StreamsListFragment.this.new AnonymousClass4(continuation);
            anonymousClass4.L$0 = obj;
            return anonymousClass4;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Stream stream, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(stream, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Stream stream = (Stream) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (!StreamsListFragment.this.isAutoPlayingStream) {
                    StreamsListFragment.this.isAutoPlayingStream = true;
                    StreamsListFragment.this.navigateToStream(stream);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @Override // com.stremio.tv.views.metadetails.RowSupportFragment, androidx.leanback.app.RowsSupportFragment, androidx.leanback.app.BaseRowSupportFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        MetaDetailsState value = getViewModel().getState().getValue();
        if (this.isAutoPlayingStream && !value.getStreamListVisible()) {
            this.isAutoPlayingStream = false;
        } else {
            requestFocus();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void navigateToStream(Stream stream) {
        NavController navControllerFindNavController = FragmentKt.findNavController(this);
        Uri uri = Uri.parse(stream.getDeepLinks().getPlayer());
        Intrinsics.checkNotNullExpressionValue(uri, "parse(...)");
        navControllerFindNavController.navigate(uri);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestFocus() {
        if (getViewModel().getState().getValue().getStreamListVisible()) {
            View view = getView();
            if (view != null) {
                view.requestFocus();
            }
            LoggingUtil loggingUtil = LoggingUtil.INSTANCE;
            String simpleName = getClass().getSimpleName();
            Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
            loggingUtil.setScreenName(simpleName);
        }
    }
}