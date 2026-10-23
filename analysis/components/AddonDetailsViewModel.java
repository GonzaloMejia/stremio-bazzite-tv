package com.stremio.common.viewmodels;

import androidx.compose.foundation.style.StylePropertiesKt;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.stremio.common.api.StremioApi;
import com.stremio.common.extensions.LoadableExtKt;
import com.stremio.common.viewmodels.state.AddonDetailsState;
import com.stremio.core.Field;
import com.stremio.core.models.AddonDetails;
import com.stremio.core.types.addon.AddonDescriptor;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Regex;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: AddonDetailsViewModel.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0014J\u0006\u0010\u0015\u001a\u00020\u0010J\u0006\u0010\u0016\u001a\u00020\u0010J\u0014\u0010\u0017\u001a\u00020\u00102\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00100\u0019J1\u0010\u001a\u001a\u00020\u00102\"\u0010\u001b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001cH\u0002¢\u0006\u0002\u0010 J\b\u0010!\u001a\u00020\u0010H\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000Ê\u0001\f\b#\u0012\b\b$\u0012\u0004\b\u0003\u0010\u0000¨\u0006\""}, d2 = {"Lcom/stremio/common/viewmodels/AddonDetailsViewModel;", "Landroidx/lifecycle/ViewModel;", "stremioApi", "Lcom/stremio/common/api/StremioApi;", "<init>", "(Lcom/stremio/common/api/StremioApi;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/stremio/common/viewmodels/state/AddonDetailsState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "addonLoadingJon", "Lkotlinx/coroutines/Job;", "loadAddonDetails", "", "addonUrl", "", "installWhenReady", "", "installAddon", "upgradeAddon", "uninstallAddon", "onRemoteLoadError", "Lkotlin/Function0;", "performAction", "action", "Lkotlin/Function2;", "Lcom/stremio/core/types/addon/AddonDescriptor;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function2;)V", "onCleared", "Stremio:common_release", "Landroidx/compose/runtime/internal/StabilityInferred;", "parameters"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class AddonDetailsViewModel extends ViewModel {
    public static final int $stable = 8;
    private final MutableStateFlow<AddonDetailsState> _state;
    private Job addonLoadingJon;
    private final StateFlow<AddonDetailsState> state;
    private final StremioApi stremioApi;

    public AddonDetailsViewModel(StremioApi stremioApi) {
        Intrinsics.checkNotNullParameter(stremioApi, "stremioApi");
        this.stremioApi = stremioApi;
        MutableStateFlow<AddonDetailsState> MutableStateFlow = StateFlowKt.MutableStateFlow(new AddonDetailsState(null, null, false, null, 15, null));
        this._state = MutableStateFlow;
        this.state = FlowKt.asStateFlow(MutableStateFlow);
    }

    public final StateFlow<AddonDetailsState> getState() {
        return this.state;
    }

    public static /* synthetic */ void loadAddonDetails$default(AddonDetailsViewModel addonDetailsViewModel, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        addonDetailsViewModel.loadAddonDetails(str, z);
    }

    public final void loadAddonDetails(String addonUrl, boolean installWhenReady) {
        AddonDescriptor descriptor;
        Intrinsics.checkNotNullParameter(addonUrl, "addonUrl");
        String strReplace = new Regex("^stremio://").replace(addonUrl, "https://");
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = (!installWhenReady || (descriptor = this.state.getValue().getDescriptor()) == null || descriptor.getInstalled()) ? false : true;
        Job job = this.addonLoadingJon;
        if (job != null) {
            Job.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.addonLoadingJon = FlowKt.launchIn(FlowKt.onEach(this.stremioApi.addonDetails(strReplace), new AnonymousClass1(booleanRef, null)), ViewModelKt.getViewModelScope(this));
    }

    /* JADX INFO: renamed from: com.stremio.common.viewmodels.AddonDetailsViewModel$loadAddonDetails$1, reason: invalid class name */
    /* JADX INFO: compiled from: AddonDetailsViewModel.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/stremio/core/models/AddonDetails;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.common.viewmodels.AddonDetailsViewModel$loadAddonDetails$1", f = "AddonDetailsViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<AddonDetails, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.BooleanRef $shouldInstall;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Ref.BooleanRef booleanRef, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$shouldInstall = booleanRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = AddonDetailsViewModel.this.new AnonymousClass1(this.$shouldInstall, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AddonDetails addonDetails, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(addonDetails, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            AddonDetails addonDetails = (AddonDetails) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                MutableStateFlow mutableStateFlow = AddonDetailsViewModel.this._state;
                AddonDetailsState value = AddonDetailsViewModel.this.getState().getValue();
                AddonDetails.Selected selected = addonDetails.getSelected();
                String transportUrl = selected != null ? selected.getTransportUrl() : null;
                AddonDescriptor asReady = LoadableExtKt.getAsReady(addonDetails.getRemoteAddon());
                if (asReady == null) {
                    asReady = addonDetails.getLocalAddon();
                }
                mutableStateFlow.setValue(value.copy(transportUrl, asReady, addonDetails.getLocalAddon() == null && LoadableExtKt.isLoading(addonDetails.getRemoteAddon()), LoadableExtKt.getAsErrorMessage(addonDetails.getRemoteAddon())));
                if (this.$shouldInstall.element && LoadableExtKt.getAsReady(addonDetails.getRemoteAddon()) != null) {
                    AddonDetailsViewModel.this.installAddon();
                    this.$shouldInstall.element = false;
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: renamed from: com.stremio.common.viewmodels.AddonDetailsViewModel$installAddon$1, reason: invalid class name */
    /* JADX INFO: compiled from: AddonDetailsViewModel.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/stremio/core/types/addon/AddonDescriptor;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.common.viewmodels.AddonDetailsViewModel$installAddon$1", f = "AddonDetailsViewModel.kt", i = {0}, l = {46}, m = "invokeSuspend", n = {"it"}, nl = {-1}, s = {"L$0"}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<AddonDescriptor, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = AddonDetailsViewModel.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AddonDescriptor addonDescriptor, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(addonDescriptor, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            AddonDescriptor addonDescriptor = (AddonDescriptor) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.L$0 = SpillingKt.nullOutSpilledVariable(addonDescriptor);
                this.label = 1;
                if (AddonDetailsViewModel.this.stremioApi.m11442installAddongIAlus(addonDescriptor, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ((Result) obj).getValue();
            }
            return Unit.INSTANCE;
        }
    }

    public final void installAddon() {
        performAction(new AnonymousClass1(null));
    }

    /* JADX INFO: renamed from: com.stremio.common.viewmodels.AddonDetailsViewModel$upgradeAddon$1, reason: invalid class name */
    /* JADX INFO: compiled from: AddonDetailsViewModel.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/stremio/core/types/addon/AddonDescriptor;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.common.viewmodels.AddonDetailsViewModel$upgradeAddon$1", f = "AddonDetailsViewModel.kt", i = {0}, l = {50}, m = "invokeSuspend", n = {"it"}, nl = {-1}, s = {"L$0"}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<AddonDescriptor, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = AddonDetailsViewModel.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AddonDescriptor addonDescriptor, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(addonDescriptor, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            AddonDescriptor addonDescriptor = (AddonDescriptor) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.L$0 = SpillingKt.nullOutSpilledVariable(addonDescriptor);
                this.label = 1;
                if (AddonDetailsViewModel.this.stremioApi.m11457upgradeAddongIAlus(addonDescriptor, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ((Result) obj).getValue();
            }
            return Unit.INSTANCE;
        }
    }

    public final void upgradeAddon() {
        performAction(new AnonymousClass1(null));
    }

    /* JADX INFO: renamed from: com.stremio.common.viewmodels.AddonDetailsViewModel$uninstallAddon$1, reason: invalid class name */
    /* JADX INFO: compiled from: AddonDetailsViewModel.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/stremio/core/types/addon/AddonDescriptor;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.common.viewmodels.AddonDetailsViewModel$uninstallAddon$1", f = "AddonDetailsViewModel.kt", i = {0, 0}, l = {StylePropertiesKt.InnerShadowId}, m = "invokeSuspend", n = {"it", "isRemoteAddonError"}, nl = {StylePropertiesKt.ContentBrushId}, s = {"L$0", "I$0"}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<AddonDescriptor, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0<Unit> $onRemoteLoadError;
        int I$0;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Function0<Unit> function0, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$onRemoteLoadError = function0;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = AddonDetailsViewModel.this.new AnonymousClass1(this.$onRemoteLoadError, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AddonDescriptor addonDescriptor, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(addonDescriptor, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            int i;
            AddonDescriptor addonDescriptor = (AddonDescriptor) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                int i3 = AddonDetailsViewModel.this.getState().getValue().getErrorMessage() != null ? 1 : 0;
                this.L$0 = SpillingKt.nullOutSpilledVariable(addonDescriptor);
                this.I$0 = i3;
                this.label = 1;
                if (AddonDetailsViewModel.this.stremioApi.m11455uninstallAddongIAlus(addonDescriptor, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                i = i3;
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                i = this.I$0;
                ResultKt.throwOnFailure(obj);
                ((Result) obj).getValue();
            }
            if (i != 0) {
                this.$onRemoteLoadError.invoke();
            }
            return Unit.INSTANCE;
        }
    }

    public final void uninstallAddon(Function0<Unit> onRemoteLoadError) {
        Intrinsics.checkNotNullParameter(onRemoteLoadError, "onRemoteLoadError");
        performAction(new AnonymousClass1(onRemoteLoadError, null));
    }

    private final void performAction(Function2<? super AddonDescriptor, ? super Continuation<? super Unit>, ? extends Object> action) {
        AddonDescriptor descriptor = this.state.getValue().getDescriptor();
        if (descriptor != null) {
            BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AddonDetailsViewModel$performAction$1$1(action, descriptor, null), 3, null);
        }
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        this.stremioApi.clearState(Field.ADDON_DETAILS.INSTANCE);
    }
}