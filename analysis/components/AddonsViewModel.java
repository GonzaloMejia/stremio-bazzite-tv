package com.stremio.common.viewmodels;

import androidx.compose.foundation.style.StylePropertiesKt;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.stremio.common.api.StremioApi;
import com.stremio.common.viewmodels.state.AddonsState;
import com.stremio.core.Field;
import com.stremio.core.models.AddonsWithFilters;
import com.stremio.core.types.addon.AddonDescriptor;
import com.stremio.core.types.addon.ResourcePath;
import com.stremio.core.types.addon.ResourceRequest;
import dev.jdtech.mpv.MPVLib;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: AddonsViewModel.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J$\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0010J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014J)\u0010\u0015\u001a\u00020\u000e2!\u0010\u0016\u001a\u001d\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u000e0\u0017J\u000e\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001eJ\b\u0010 \u001a\u00020\u000eH\u0002J\u0010\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020\u000eH\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fÊ\u0001\f\b&\u0012\b\b'\u0012\u0004\b\u0003\u0010\u0000¨\u0006%"}, d2 = {"Lcom/stremio/common/viewmodels/AddonsViewModel;", "Landroidx/lifecycle/ViewModel;", "stremioApi", "Lcom/stremio/common/api/StremioApi;", "<init>", "(Lcom/stremio/common/api/StremioApi;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/stremio/common/viewmodels/state/AddonsState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "loadAddons", "", "type", "", "addonUrl", "catalogId", "request", "Lcom/stremio/core/types/addon/ResourceRequest;", "pullAddons", "callback", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "result", "installAddon", "descriptor", "Lcom/stremio/core/types/addon/AddonDescriptor;", "uninstallAddon", "syncAddons", "toState", "addonsWithFilters", "Lcom/stremio/core/models/AddonsWithFilters;", "onCleared", "Stremio:common_release", "Landroidx/compose/runtime/internal/StabilityInferred;", "parameters"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class AddonsViewModel extends ViewModel {
    public static final int $stable = 8;
    private final MutableStateFlow<AddonsState> _state;
    private final StateFlow<AddonsState> state;
    private final StremioApi stremioApi;

    public AddonsViewModel(StremioApi stremioApi) {
        Intrinsics.checkNotNullParameter(stremioApi, "stremioApi");
        this.stremioApi = stremioApi;
        MutableStateFlow<AddonsState> MutableStateFlow = StateFlowKt.MutableStateFlow(new AddonsState(null, null, null, null, 15, null));
        this._state = MutableStateFlow;
        this.state = FlowKt.asStateFlow(MutableStateFlow);
        syncAddons();
        FlowKt.launchIn(FlowKt.onEach(new AddonsViewModel$special$$inlined$map$1(stremioApi.addons(), this), new AnonymousClass2(null)), ViewModelKt.getViewModelScope(this));
    }

    public final StateFlow<AddonsState> getState() {
        return this.state;
    }

    /* JADX INFO: renamed from: com.stremio.common.viewmodels.AddonsViewModel$2, reason: invalid class name */
    /* JADX INFO: compiled from: AddonsViewModel.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/stremio/common/viewmodels/state/AddonsState;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.common.viewmodels.AddonsViewModel$2", f = "AddonsViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<AddonsState, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = AddonsViewModel.this.new AnonymousClass2(continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AddonsState addonsState, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(addonsState, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            AddonsState addonsState = (AddonsState) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            AddonsViewModel.this._state.setValue(addonsState);
            return Unit.INSTANCE;
        }
    }

    public final void loadAddons(String type, String addonUrl, String catalogId) {
        if (type != null) {
            if (addonUrl == null) {
                addonUrl = "";
            }
            String str = addonUrl;
            loadAddons(new ResourceRequest(str, new ResourcePath("addon_catalog", type, catalogId == null ? "" : catalogId, CollectionsKt.emptyList(), null, 16, null), null, 4, null));
        }
    }

    public final void loadAddons(ResourceRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        if (Intrinsics.areEqual(this.state.getValue().getCurrentRequest(), request)) {
            return;
        }
        this._state.setValue(AddonsState.copy$default(this.state.getValue(), null, null, null, request, 7, null));
        this.stremioApi.loadAddons(request);
    }

    /* JADX INFO: renamed from: com.stremio.common.viewmodels.AddonsViewModel$pullAddons$1, reason: invalid class name */
    /* JADX INFO: compiled from: AddonsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.common.viewmodels.AddonsViewModel$pullAddons$1", f = "AddonsViewModel.kt", i = {}, l = {55}, m = "invokeSuspend", n = {}, nl = {StylePropertiesKt.InnerShadowId}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Boolean, Unit> $callback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Function1<? super Boolean, Unit> function1, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$callback = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AddonsViewModel.this.new AnonymousClass1(this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object objM11450syncAddonsIoAF18A;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                objM11450syncAddonsIoAF18A = AddonsViewModel.this.stremioApi.m11450syncAddonsIoAF18A(this);
                if (objM11450syncAddonsIoAF18A == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                objM11450syncAddonsIoAF18A = ((Result) obj).getValue();
            }
            Function1<Boolean, Unit> function1 = this.$callback;
            if (Result.m11873isSuccessimpl(objM11450syncAddonsIoAF18A)) {
                function1.invoke(Boxing.boxBoolean(true));
            }
            Function1<Boolean, Unit> function2 = this.$callback;
            if (Result.m11869exceptionOrNullimpl(objM11450syncAddonsIoAF18A) != null) {
                function2.invoke(Boxing.boxBoolean(false));
            }
            return Unit.INSTANCE;
        }
    }

    public final void pullAddons(Function1<? super Boolean, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(callback, null), 3, null);
    }

    /* JADX INFO: renamed from: com.stremio.common.viewmodels.AddonsViewModel$installAddon$1, reason: invalid class name */
    /* JADX INFO: compiled from: AddonsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.common.viewmodels.AddonsViewModel$installAddon$1", f = "AddonsViewModel.kt", i = {}, l = {63}, m = "invokeSuspend", n = {}, nl = {64}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ AddonDescriptor $descriptor;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(AddonDescriptor addonDescriptor, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$descriptor = addonDescriptor;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AddonsViewModel.this.new AnonymousClass1(this.$descriptor, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (AddonsViewModel.this.stremioApi.m11442installAddongIAlus(this.$descriptor, this) == coroutine_suspended) {
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

    public final void installAddon(AddonDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(descriptor, null), 3, null);
    }

    /* JADX INFO: renamed from: com.stremio.common.viewmodels.AddonsViewModel$uninstallAddon$1, reason: invalid class name */
    /* JADX INFO: compiled from: AddonsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.common.viewmodels.AddonsViewModel$uninstallAddon$1", f = "AddonsViewModel.kt", i = {}, l = {69}, m = "invokeSuspend", n = {}, nl = {MPVLib.MpvLogLevel.MPV_LOG_LEVEL_TRACE}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ AddonDescriptor $descriptor;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(AddonDescriptor addonDescriptor, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$descriptor = addonDescriptor;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AddonsViewModel.this.new AnonymousClass1(this.$descriptor, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (AddonsViewModel.this.stremioApi.m11455uninstallAddongIAlus(this.$descriptor, this) == coroutine_suspended) {
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

    public final void uninstallAddon(AddonDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(descriptor, null), 3, null);
    }

    /* JADX INFO: renamed from: com.stremio.common.viewmodels.AddonsViewModel$syncAddons$1, reason: invalid class name */
    /* JADX INFO: compiled from: AddonsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.common.viewmodels.AddonsViewModel$syncAddons$1", f = "AddonsViewModel.kt", i = {}, l = {74}, m = "invokeSuspend", n = {}, nl = {-1}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AddonsViewModel.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (AddonsViewModel.this.stremioApi.m11450syncAddonsIoAF18A(this) == coroutine_suspended) {
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

    private final void syncAddons() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AddonsState toState(AddonsWithFilters addonsWithFilters) {
        return AddonsState.copy$default(this.state.getValue(), addonsWithFilters.getSelectable().getTypes(), addonsWithFilters.getSelectable().getCatalogs(), addonsWithFilters.getCatalog(), null, 8, null);
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        this.stremioApi.clearState(Field.ADDONS.INSTANCE);
    }
}