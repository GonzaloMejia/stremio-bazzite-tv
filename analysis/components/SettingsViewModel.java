package com.stremio.common.viewmodels;

import androidx.compose.foundation.style.StylePropertiesKt;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;
import com.stremio.common.api.StremioApi;
import com.stremio.common.extensions.LoadableExtKt;
import com.stremio.core.Core;
import com.stremio.core.Field;
import com.stremio.core.models.Ctx;
import com.stremio.core.models.StreamingServer;
import com.stremio.core.runtime.EnvError;
import com.stremio.core.types.StreamPreset;
import com.stremio.core.types.StreamPresetsBucket;
import com.stremio.core.types.profile.Auth;
import com.stremio.core.types.profile.Profile;
import com.stremio.translations.Locales;
import com.stremio.tv.Constants;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.full.KClasses;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.koin.mp.KoinPlatformTools;
import pbandk.Message;
import pbandk.MessageKt;

/* JADX INFO: compiled from: SettingsViewModel.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u0000 02\u00020\u0001:\u00010B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u001eH\u0002J\u0006\u0010 \u001a\u00020\u001eJ\u0012\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010\u000f\u001a\u0004\u0018\u00010#J\u000e\u0010$\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\"J\u001a\u0010&\u001a\u00020\u001e2\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0(J\u0010\u0010)\u001a\u00020\u001e2\b\u0010*\u001a\u0004\u0018\u00010\"J\u0015\u0010+\u001a\u00020\u001e2\b\u0010,\u001a\u0004\u0018\u00010-¢\u0006\u0002\u0010.J\u001c\u0010/\u001a\u00020\u001e2\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020#0(H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0016\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\fR\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\n¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\fR\u001a\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00190\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00190\n¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\fÊ\u0001\f\b2\u0012\b\b3\u0012\u0004\b\u0003\u0010\u0000¨\u00061"}, d2 = {"Lcom/stremio/common/viewmodels/SettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "stremioApi", "Lcom/stremio/common/api/StremioApi;", "<init>", "(Lcom/stremio/common/api/StremioApi;)V", "_streamingServer", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/stremio/core/models/StreamingServer;", "streamingServer", "Lkotlinx/coroutines/flow/StateFlow;", "getStreamingServer", "()Lkotlinx/coroutines/flow/StateFlow;", "_settings", "Lcom/stremio/core/types/profile/Profile$Settings;", "settings", "getSettings", "_authState", "Lcom/stremio/core/types/profile/Auth;", "authState", "getAuthState", "hasPremium", "", "getHasPremium", "_streamPresets", "", "Lcom/stremio/core/types/StreamPreset;", "streamPresets", "getStreamPresets", "loadProfile", "", "loadStreamingServer", "reloadStreamingServer", "getTorrentProfile", "", "Lcom/stremio/core/models/StreamingServer$Settings;", "selectStreamPreset", "presetId", "updateSettings", "action", "Lkotlin/Function1;", "updateTorrentProfile", "torrentProfile", "updateCacheSize", "cacheSize", "", "(Ljava/lang/Double;)V", "updateServerSettings", "Companion", "Stremio:common_release", "Landroidx/compose/runtime/internal/StabilityInferred;", "parameters"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class SettingsViewModel extends ViewModel {
    public static final String DEFAULT_PROFILE = "DEFAULT";
    public static final String FAST_PROFILE = "FAST";
    public static final String SOFT_PROFILE = "SOFT";
    public static final String ULTRA_FAST_PROFILE = "ULTRA_FAST";
    private final MutableStateFlow<Auth> _authState;
    private final MutableStateFlow<Profile.Settings> _settings;
    private final MutableStateFlow<List<StreamPreset>> _streamPresets;
    private final MutableStateFlow<StreamingServer> _streamingServer;
    private final StateFlow<Auth> authState;
    private final StateFlow<Boolean> hasPremium;
    private final StateFlow<Profile.Settings> settings;
    private final StateFlow<List<StreamPreset>> streamPresets;
    private final StateFlow<StreamingServer> streamingServer;
    private final StremioApi stremioApi;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final Map<String, Locale> LOCALE_OVERRIDES = MapsKt.mapOf(new Pair("pob", Locale.forLanguageTag(Locales.PT_BR)), new Pair("zht", Locale.forLanguageTag(Locales.ZH_TW)), new Pair("lat", Locale.forLanguageTag("es-419")));

    /* JADX WARN: Multi-variable type inference failed */
    public SettingsViewModel() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public SettingsViewModel(StremioApi stremioApi) {
        Intrinsics.checkNotNullParameter(stremioApi, "stremioApi");
        this.stremioApi = stremioApi;
        MutableStateFlow<StreamingServer> MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this._streamingServer = MutableStateFlow;
        this.streamingServer = FlowKt.asStateFlow(MutableStateFlow);
        MutableStateFlow<Profile.Settings> MutableStateFlow2 = StateFlowKt.MutableStateFlow(null);
        this._settings = MutableStateFlow2;
        this.settings = FlowKt.asStateFlow(MutableStateFlow2);
        MutableStateFlow<Auth> MutableStateFlow3 = StateFlowKt.MutableStateFlow(null);
        this._authState = MutableStateFlow3;
        StateFlow<Auth> stateFlowAsStateFlow = FlowKt.asStateFlow(MutableStateFlow3);
        this.authState = stateFlowAsStateFlow;
        SettingsViewModel$special$$inlined$map$1 settingsViewModel$special$$inlined$map$1 = new SettingsViewModel$special$$inlined$map$1(stateFlowAsStateFlow);
        SettingsViewModel settingsViewModel = this;
        this.hasPremium = FlowKt.stateIn(settingsViewModel$special$$inlined$map$1, ViewModelKt.getViewModelScope(settingsViewModel), SharingStarted.INSTANCE.getEagerly(), false);
        MutableStateFlow<List<StreamPreset>> MutableStateFlow4 = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this._streamPresets = MutableStateFlow4;
        this.streamPresets = FlowKt.asStateFlow(MutableStateFlow4);
        loadProfile();
        loadStreamingServer();
        FlowKt.launchIn(FlowKt.onEach(FlowKt.distinctUntilChanged(stremioApi.ctx()), new AnonymousClass1(null)), ViewModelKt.getViewModelScope(settingsViewModel));
    }

    /* JADX INFO: compiled from: SettingsViewModel.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R%\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/stremio/common/viewmodels/SettingsViewModel$Companion;", "", "<init>", "()V", "DEFAULT_PROFILE", "", "SOFT_PROFILE", "FAST_PROFILE", "ULTRA_FAST_PROFILE", "LOCALE_OVERRIDES", "", "Ljava/util/Locale;", "kotlin.jvm.PlatformType", "getLOCALE_OVERRIDES", "()Ljava/util/Map;", "Stremio:common_release"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Map<String, Locale> getLOCALE_OVERRIDES() {
            return SettingsViewModel.LOCALE_OVERRIDES;
        }
    }

    public final StateFlow<StreamingServer> getStreamingServer() {
        return this.streamingServer;
    }

    public final StateFlow<Profile.Settings> getSettings() {
        return this.settings;
    }

    public final StateFlow<Auth> getAuthState() {
        return this.authState;
    }

    public final StateFlow<Boolean> getHasPremium() {
        return this.hasPremium;
    }

    public final StateFlow<List<StreamPreset>> getStreamPresets() {
        return this.streamPresets;
    }

    /* JADX INFO: renamed from: com.stremio.common.viewmodels.SettingsViewModel$1, reason: invalid class name */
    /* JADX INFO: compiled from: SettingsViewModel.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/stremio/core/models/Ctx;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.common.viewmodels.SettingsViewModel$1", f = "SettingsViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<Ctx, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = SettingsViewModel.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Ctx ctx, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(ctx, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Ctx ctx = (Ctx) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                SettingsViewModel.this._authState.setValue(ctx.getProfile().getAuth());
                SettingsViewModel.this._settings.setValue(ctx.getProfile().getSettings());
                MutableStateFlow mutableStateFlow = SettingsViewModel.this._streamPresets;
                StreamPresetsBucket streamPresets = ctx.getStreamPresets();
                List<StreamPreset> presets = streamPresets != null ? streamPresets.getPresets() : null;
                if (presets == null) {
                    presets = CollectionsKt.emptyList();
                }
                mutableStateFlow.setValue(presets);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: renamed from: com.stremio.common.viewmodels.SettingsViewModel$loadProfile$1, reason: invalid class name */
    /* JADX INFO: compiled from: SettingsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.common.viewmodels.SettingsViewModel$loadProfile$1", f = "SettingsViewModel.kt", i = {0, 0}, l = {ModuleDescriptor.MODULE_VERSION}, m = "invokeSuspend", n = {"this_$iv", "field$iv"}, nl = {186}, s = {"L$0", "L$1"}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SettingsViewModel.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Field field;
            Message messageDecodeFromByteArray;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StremioApi stremioApi = SettingsViewModel.this.stremioApi;
                Field.CTX ctx = Field.CTX.INSTANCE;
                this.L$0 = SpillingKt.nullOutSpilledVariable(stremioApi);
                this.L$1 = ctx;
                this.label = 1;
                obj = stremioApi.getLoadCore().await(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                field = ctx;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                field = (Field) this.L$1;
                ResultKt.throwOnFailure(obj);
            }
            if (((EnvError) obj) == null) {
                byte[] stateNative = Core.INSTANCE.getStateNative(field);
                Object companionObjectInstance = KClasses.getCompanionObjectInstance(Reflection.getOrCreateKotlinClass(Ctx.class));
                Intrinsics.checkNotNull(companionObjectInstance, "null cannot be cast to non-null type pbandk.Message.Companion<T of com.stremio.core.Core.getState>");
                messageDecodeFromByteArray = MessageKt.decodeFromByteArray((Message.Companion) companionObjectInstance, stateNative);
            } else {
                messageDecodeFromByteArray = null;
            }
            Ctx ctx2 = (Ctx) messageDecodeFromByteArray;
            if (ctx2 != null) {
                SettingsViewModel settingsViewModel = SettingsViewModel.this;
                settingsViewModel._authState.setValue(ctx2.getProfile().getAuth());
                settingsViewModel._settings.setValue(ctx2.getProfile().getSettings());
                MutableStateFlow mutableStateFlow = settingsViewModel._streamPresets;
                StreamPresetsBucket streamPresets = ctx2.getStreamPresets();
                List<StreamPreset> presets = streamPresets != null ? streamPresets.getPresets() : null;
                if (presets == null) {
                    presets = CollectionsKt.emptyList();
                }
                mutableStateFlow.setValue(presets);
            }
            return Unit.INSTANCE;
        }
    }

    private final void loadProfile() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(null), 3, null);
    }

    /* JADX INFO: renamed from: com.stremio.common.viewmodels.SettingsViewModel$loadStreamingServer$1, reason: invalid class name */
    /* JADX INFO: compiled from: SettingsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.common.viewmodels.SettingsViewModel$loadStreamingServer$1", f = "SettingsViewModel.kt", i = {}, l = {83}, m = "invokeSuspend", n = {}, nl = {84}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SettingsViewModel.this.new AnonymousClass1(continuation);
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
                obj = SettingsViewModel.this.stremioApi.streamingServer(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            SettingsViewModel.this._streamingServer.setValue((StreamingServer) obj);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadStreamingServer() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(null), 3, null);
    }

    /* JADX INFO: renamed from: com.stremio.common.viewmodels.SettingsViewModel$reloadStreamingServer$1, reason: invalid class name */
    /* JADX INFO: compiled from: SettingsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.common.viewmodels.SettingsViewModel$reloadStreamingServer$1", f = "SettingsViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SettingsViewModel.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                SettingsViewModel.this.stremioApi.reloadStreamingServer();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final void reloadStreamingServer() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(null), 3, null);
    }

    public final String getTorrentProfile(StreamingServer.Settings settings) {
        Long lValueOf = settings != null ? Long.valueOf(settings.getBtMaxConnections()) : null;
        if (lValueOf != null && lValueOf.longValue() == 35) {
            return SOFT_PROFILE;
        }
        if (lValueOf != null && lValueOf.longValue() == 55) {
            return DEFAULT_PROFILE;
        }
        if (lValueOf != null && lValueOf.longValue() == 200) {
            return FAST_PROFILE;
        }
        if (lValueOf != null && lValueOf.longValue() == 400) {
            return ULTRA_FAST_PROFILE;
        }
        return null;
    }

    public final void selectStreamPreset(String presetId) {
        Intrinsics.checkNotNullParameter(presetId, "presetId");
        this.stremioApi.selectStreamPreset(presetId);
    }

    /* JADX INFO: renamed from: com.stremio.common.viewmodels.SettingsViewModel$updateSettings$1, reason: invalid class name */
    /* JADX INFO: compiled from: SettingsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.common.viewmodels.SettingsViewModel$updateSettings$1", f = "SettingsViewModel.kt", i = {0, 0}, l = {StylePropertiesKt.TextDirectionMask}, m = "invokeSuspend", n = {"it", "updatedSettings"}, nl = {113}, s = {"L$1", "L$2"}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Profile.Settings, Profile.Settings> $action;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Function1<? super Profile.Settings, Profile.Settings> function1, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$action = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SettingsViewModel.this.new AnonymousClass1(this.$action, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Profile.Settings settings;
            Profile.Settings settings2;
            SettingsViewModel settingsViewModel;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                settings = (Profile.Settings) SettingsViewModel.this._settings.getValue();
                if (settings != null) {
                    Function1<Profile.Settings, Profile.Settings> function1 = this.$action;
                    SettingsViewModel settingsViewModel2 = SettingsViewModel.this;
                    Profile.Settings settingsInvoke = function1.invoke(settings);
                    StremioApi stremioApi = settingsViewModel2.stremioApi;
                    this.L$0 = settingsViewModel2;
                    this.L$1 = settings;
                    this.L$2 = settingsInvoke;
                    this.label = 1;
                    if (stremioApi.m11456updateSettingsgIAlus(settingsInvoke, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    settings2 = settingsInvoke;
                    settingsViewModel = settingsViewModel2;
                }
                return Unit.INSTANCE;
            }
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            settings2 = (Profile.Settings) this.L$2;
            settings = (Profile.Settings) this.L$1;
            settingsViewModel = (SettingsViewModel) this.L$0;
            ResultKt.throwOnFailure(obj);
            ((Result) obj).getValue();
            if (!Intrinsics.areEqual(settings.getStreamingServerUrl(), settings2.getStreamingServerUrl())) {
                settingsViewModel.loadStreamingServer();
            }
            return Unit.INSTANCE;
        }
    }

    public final void updateSettings(Function1<? super Profile.Settings, Profile.Settings> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(action, null), 3, null);
    }

    public final void updateTorrentProfile(final String torrentProfile) {
        updateServerSettings(new Function1() { // from class: com.stremio.common.viewmodels.SettingsViewModel$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingsViewModel.updateTorrentProfile$lambda$0(torrentProfile, (StreamingServer.Settings) obj);
            }
        });
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    static final StreamingServer.Settings updateTorrentProfile$lambda$0(String str, StreamingServer.Settings it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (str != null) {
            switch (str.hashCode()) {
                case -2032180703:
                    if (str.equals(DEFAULT_PROFILE)) {
                        return it.copy((8135 & 1) != 0 ? it.appPath : null, (8135 & 2) != 0 ? it.cacheRoot : null, (8135 & 4) != 0 ? it.serverVersion : null, (8135 & 8) != 0 ? it.remoteHttps : null, (8135 & 16) != 0 ? it.transcodeProfile : null, (8135 & 32) != 0 ? it.cacheSize : null, (8135 & 64) != 0 ? it.proxyStreamsEnabled : false, (8135 & 128) != 0 ? it.btMaxConnections : 55L, (8135 & 256) != 0 ? it.btHandshakeTimeout : 20000L, (8135 & 512) != 0 ? it.btRequestTimeout : 4000L, (8135 & 1024) != 0 ? it.btDownloadSpeedSoftLimit : 2621440.0d, (8135 & 2048) != 0 ? it.btDownloadSpeedHardLimit : 3670016.0d, (8135 & 4096) != 0 ? it.btMinPeersForStable : 5L, (8135 & 8192) != 0 ? it.unknownFields : null);
                    }
                    break;
                case -1667514065:
                    if (str.equals(ULTRA_FAST_PROFILE)) {
                        return it.copy((8135 & 1) != 0 ? it.appPath : null, (8135 & 2) != 0 ? it.cacheRoot : null, (8135 & 4) != 0 ? it.serverVersion : null, (8135 & 8) != 0 ? it.remoteHttps : null, (8135 & 16) != 0 ? it.transcodeProfile : null, (8135 & 32) != 0 ? it.cacheSize : null, (8135 & 64) != 0 ? it.proxyStreamsEnabled : false, (8135 & 128) != 0 ? it.btMaxConnections : 400L, (8135 & 256) != 0 ? it.btHandshakeTimeout : 25000L, (8135 & 512) != 0 ? it.btRequestTimeout : 6000L, (8135 & 1024) != 0 ? it.btDownloadSpeedSoftLimit : 8388608.0d, (8135 & 2048) != 0 ? it.btDownloadSpeedHardLimit : 7.86432E7d, (8135 & 4096) != 0 ? it.btMinPeersForStable : 10L, (8135 & 8192) != 0 ? it.unknownFields : null);
                    }
                    break;
                case 2150492:
                    if (str.equals(FAST_PROFILE)) {
                        return it.copy((8135 & 1) != 0 ? it.appPath : null, (8135 & 2) != 0 ? it.cacheRoot : null, (8135 & 4) != 0 ? it.serverVersion : null, (8135 & 8) != 0 ? it.remoteHttps : null, (8135 & 16) != 0 ? it.transcodeProfile : null, (8135 & 32) != 0 ? it.cacheSize : null, (8135 & 64) != 0 ? it.proxyStreamsEnabled : false, (8135 & 128) != 0 ? it.btMaxConnections : 200L, (8135 & 256) != 0 ? it.btHandshakeTimeout : 20000L, (8135 & 512) != 0 ? it.btRequestTimeout : 4000L, (8135 & 1024) != 0 ? it.btDownloadSpeedSoftLimit : 4194304.0d, (8135 & 2048) != 0 ? it.btDownloadSpeedHardLimit : 3.93216E7d, (8135 & 4096) != 0 ? it.btMinPeersForStable : 10L, (8135 & 8192) != 0 ? it.unknownFields : null);
                    }
                    break;
                case 2550826:
                    if (str.equals(SOFT_PROFILE)) {
                        return it.copy((8135 & 1) != 0 ? it.appPath : null, (8135 & 2) != 0 ? it.cacheRoot : null, (8135 & 4) != 0 ? it.serverVersion : null, (8135 & 8) != 0 ? it.remoteHttps : null, (8135 & 16) != 0 ? it.transcodeProfile : null, (8135 & 32) != 0 ? it.cacheSize : null, (8135 & 64) != 0 ? it.proxyStreamsEnabled : false, (8135 & 128) != 0 ? it.btMaxConnections : 35L, (8135 & 256) != 0 ? it.btHandshakeTimeout : 20000L, (8135 & 512) != 0 ? it.btRequestTimeout : 4000L, (8135 & 1024) != 0 ? it.btDownloadSpeedSoftLimit : 1677721.6d, (8135 & 2048) != 0 ? it.btDownloadSpeedHardLimit : 1677721.6d, (8135 & 4096) != 0 ? it.btMinPeersForStable : 5L, (8135 & 8192) != 0 ? it.unknownFields : null);
                    }
                    break;
            }
        }
        return it;
    }

    public final void updateCacheSize(final Double cacheSize) {
        updateServerSettings(new Function1() { // from class: com.stremio.common.viewmodels.SettingsViewModel$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingsViewModel.updateCacheSize$lambda$0(cacheSize, (StreamingServer.Settings) obj);
            }
        });
    }

    static final StreamingServer.Settings updateCacheSize$lambda$0(Double d, StreamingServer.Settings it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.copy((8135 & 1) != 0 ? it.appPath : null, (8135 & 2) != 0 ? it.cacheRoot : null, (8135 & 4) != 0 ? it.serverVersion : null, (8135 & 8) != 0 ? it.remoteHttps : null, (8135 & 16) != 0 ? it.transcodeProfile : null, (8135 & 32) != 0 ? it.cacheSize : d, (8135 & 64) != 0 ? it.proxyStreamsEnabled : false, (8135 & 128) != 0 ? it.btMaxConnections : 0L, (8135 & 256) != 0 ? it.btHandshakeTimeout : 0L, (8135 & 512) != 0 ? it.btRequestTimeout : 0L, (8135 & 1024) != 0 ? it.btDownloadSpeedSoftLimit : Constants.MIN_SUBTITLES_OFFSET, (8135 & 2048) != 0 ? it.btDownloadSpeedHardLimit : Constants.MIN_SUBTITLES_OFFSET, (8135 & 4096) != 0 ? it.btMinPeersForStable : 0L, (8135 & 8192) != 0 ? it.unknownFields : null);
    }

    /* JADX INFO: renamed from: com.stremio.common.viewmodels.SettingsViewModel$updateServerSettings$1, reason: invalid class name */
    /* JADX INFO: compiled from: SettingsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.common.viewmodels.SettingsViewModel$updateServerSettings$1", f = "SettingsViewModel.kt", i = {0, 0}, l = {178}, m = "invokeSuspend", n = {"it", "modifiedSettings"}, nl = {179}, s = {"L$1", "L$2"}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<StreamingServer.Settings, StreamingServer.Settings> $action;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Function1<? super StreamingServer.Settings, StreamingServer.Settings> function1, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$action = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SettingsViewModel.this.new AnonymousClass1(this.$action, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            StreamingServer.Settings asReady;
            SettingsViewModel settingsViewModel;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StreamingServer value = SettingsViewModel.this.getStreamingServer().getValue();
                if (value != null && (asReady = LoadableExtKt.getAsReady(value.getSettings())) != null) {
                    Function1<StreamingServer.Settings, StreamingServer.Settings> function1 = this.$action;
                    SettingsViewModel settingsViewModel2 = SettingsViewModel.this;
                    StreamingServer.Settings settingsInvoke = function1.invoke(asReady);
                    StremioApi stremioApi = settingsViewModel2.stremioApi;
                    this.L$0 = settingsViewModel2;
                    this.L$1 = SpillingKt.nullOutSpilledVariable(asReady);
                    this.L$2 = SpillingKt.nullOutSpilledVariable(settingsInvoke);
                    this.label = 1;
                    obj = stremioApi.updateStreamingServerSettings(settingsInvoke, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    settingsViewModel = settingsViewModel2;
                }
                return Unit.INSTANCE;
            }
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            settingsViewModel = (SettingsViewModel) this.L$0;
            ResultKt.throwOnFailure(obj);
            settingsViewModel._streamingServer.setValue((StreamingServer) obj);
            return Unit.INSTANCE;
        }
    }

    private final void updateServerSettings(Function1<? super StreamingServer.Settings, StreamingServer.Settings> action) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(action, null), 3, null);
    }

    public /* synthetic */ SettingsViewModel(StremioApi stremioApi, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (StremioApi) KoinPlatformTools.INSTANCE.defaultContext().get().getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(StremioApi.class), null, null) : stremioApi);
    }
}