package com.stremio.common.viewmodels;

import android.net.Uri;
import androidx.core.app.FrameMetricsAggregator;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.media3.common.C;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.common.Scopes;
import com.stremio.common.api.StreamingServerApi;
import com.stremio.common.api.StremioApi;
import com.stremio.common.extensions.LoadableExtKt;
import com.stremio.common.extensions.ProfileExtKt;
import com.stremio.common.platform.MediaInfoLoader;
import com.stremio.common.platform.MediaInfoResult;
import com.stremio.common.platform.PlatformKt;
import com.stremio.common.players.Chapter;
import com.stremio.common.players.ChapterType;
import com.stremio.common.utils.Throttle;
import com.stremio.common.viewmodels.state.PlayerState;
import com.stremio.common.viewmodels.state.PlayerType;
import com.stremio.common.viewmodels.state.SkipSegment;
import com.stremio.common.viewmodels.state.VideoPlaybackState;
import com.stremio.core.Core;
import com.stremio.core.Field;
import com.stremio.core.models.Ctx;
import com.stremio.core.models.LoadableConvertedStream;
import com.stremio.core.models.LoadableSubtitles;
import com.stremio.core.models.Player;
import com.stremio.core.types.addon.ResourcePath;
import com.stremio.core.types.addon.ResourceRequest;
import com.stremio.core.types.library.LibraryItem;
import com.stremio.core.types.library.LibraryItemState;
import com.stremio.core.types.profile.Auth;
import com.stremio.core.types.profile.Profile;
import com.stremio.core.types.resource.MetaItem;
import com.stremio.core.types.resource.Stream;
import com.stremio.core.types.resource.Video;
import com.stremio.core.types.subtitle.Subtitle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.SortedMap;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.full.KClasses;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.io.files.FileSystemKt;
import org.koin.mp.KoinPlatformTools;
import pbandk.Message;
import pbandk.MessageKt;

/* JADX INFO: compiled from: PlayerViewModel.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000Ò\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u0000 W2\u00020\u0001:\u0001WB\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J@\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010#2\b\u0010%\u001a\u0004\u0018\u00010#2\b\u0010&\u001a\u0004\u0018\u00010#2\b\u0010'\u001a\u0004\u0018\u00010#2\b\u0010(\u001a\u0004\u0018\u00010#J\u0016\u0010)\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-J\u0016\u0010.\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-J\u0006\u0010/\u001a\u00020\u001eJ\u000e\u00100\u001a\u0002012\u0006\u00102\u001a\u000203J\u001a\u00104\u001a\u00020\u001e2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u001e06J\u000e\u00107\u001a\u00020\u001e2\u0006\u00108\u001a\u000209J\u0015\u0010:\u001a\u00020\u001e2\b\u0010;\u001a\u0004\u0018\u00010<¢\u0006\u0002\u0010=J\u001a\u0010>\u001a\u00020\u001e2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020?\u0012\u0004\u0012\u00020?06J\u0006\u0010@\u001a\u00020\u001eJ\u0010\u0010A\u001a\u00020\u001e2\u0006\u0010B\u001a\u00020#H\u0002J\u0010\u0010C\u001a\u00020\u001e2\u0006\u0010B\u001a\u00020#H\u0002J\u0010\u0010D\u001a\u0004\u0018\u00010#H\u0082@¢\u0006\u0002\u0010EJ\u0014\u0010F\u001a\u00020\u001e2\f\u0010G\u001a\b\u0012\u0004\u0012\u00020I0HJ\u0010\u0010J\u001a\u00020\u001e2\u0006\u0010K\u001a\u00020LH\u0002J4\u0010M\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010O\u0012\u0006\u0012\u0004\u0018\u00010O0N2\u000e\b\u0002\u0010G\u001a\b\u0012\u0004\u0012\u00020I0H2\n\b\u0002\u0010P\u001a\u0004\u0018\u00010QH\u0002J\u0006\u0010R\u001a\u00020\u001eJ\u0018\u0010S\u001a\u0012\u0012\u0004\u0012\u00020U0Tj\b\u0012\u0004\u0012\u00020U`VH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cÊ\u0001\f\bY\u0012\b\bZ\u0012\u0004\b\u0003\u0010\u0000¨\u0006X"}, d2 = {"Lcom/stremio/common/viewmodels/PlayerViewModel;", "Landroidx/lifecycle/ViewModel;", "stremioApi", "Lcom/stremio/common/api/StremioApi;", "streamingServerApi", "Lcom/stremio/common/api/StreamingServerApi;", "<init>", "(Lcom/stremio/common/api/StremioApi;Lcom/stremio/common/api/StreamingServerApi;)V", "loadPlayerJob", "Lkotlinx/coroutines/Job;", "loadMediaInfoJob", "loadVideoParamsJob", "playerTimeChangedThrottle", "Lcom/stremio/common/utils/Throttle;", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/stremio/common/viewmodels/state/PlayerState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", Scopes.PROFILE, "Lcom/stremio/core/types/profile/Profile;", "settings", "Lcom/stremio/core/types/profile/Profile$Settings;", "getSettings", "()Lcom/stremio/core/types/profile/Profile$Settings;", "setSettings", "(Lcom/stremio/core/types/profile/Profile$Settings;)V", "onStateChange", "", "playbackState", "Lcom/stremio/common/viewmodels/state/VideoPlaybackState;", "load", "streamData", "", "streamAddonUrl", "metaAddonUrl", "type", TtmlNode.ATTR_ID, "videoId", "markVideoAsWatched", "video", "Lcom/stremio/core/types/resource/Video;", "watched", "", "markSeasonAsWatched", "markCurrentVideoAsWatched", "proxiedSubtitlesUri", "Landroid/net/Uri;", "subtitle", "Lcom/stremio/core/types/subtitle/Subtitle;", "resolveTorrentExternalUrl", "action", "Lkotlin/Function1;", "updatePlayer", "playerType", "Lcom/stremio/common/viewmodels/state/PlayerType;", "updateInitialPosition", "initialPosition", "", "(Ljava/lang/Long;)V", "updateStreamState", "Lcom/stremio/core/models/Player$StreamState;", "startMediaLoadJobs", "loadMediaInfo", "streamUrl", "loadVideoParams", "resolveFilename", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateChapters", "chapters", "", "Lcom/stremio/common/players/Chapter;", "updatePlayerState", "player", "Lcom/stremio/core/models/Player;", "updateSkipSegments", "Lkotlin/Pair;", "Lcom/stremio/common/viewmodels/state/SkipSegment;", "introOutro", "Lcom/stremio/core/models/Player$IntroOutro;", "unload", "subtitlesLocaleComparator", "Ljava/util/Comparator;", "Ljava/util/Locale;", "Lkotlin/Comparator;", "Companion", "Stremio:common_release", "Landroidx/compose/runtime/internal/StabilityInferred;", "parameters"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class PlayerViewModel extends ViewModel {
    public static final float EXTERNAL_PLAYER_WATCHED_COEF = 0.7f;
    private final MutableStateFlow<PlayerState> _state;
    private Job loadMediaInfoJob;
    private Job loadPlayerJob;
    private Job loadVideoParamsJob;
    private final Throttle playerTimeChangedThrottle;
    private final Profile profile;
    private Profile.Settings settings;
    private final StateFlow<PlayerState> state;
    private final StreamingServerApi streamingServerApi;
    private final StremioApi stremioApi;
    public static final int $stable = 8;

    /* JADX INFO: renamed from: com.stremio.common.viewmodels.PlayerViewModel$resolveFilename$1, reason: invalid class name */
    /* JADX INFO: compiled from: PlayerViewModel.kt */
    @Metadata(k = 3, mv = {2, 4, 0}, xi = 560)
    @DebugMetadata(c = "com.stremio.common.viewmodels.PlayerViewModel", f = "PlayerViewModel.kt", i = {0}, l = {211}, m = "resolveFilename", n = {"stream"}, nl = {214}, s = {"L$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PlayerViewModel.this.resolveFilename(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PlayerViewModel() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public PlayerViewModel(StremioApi stremioApi, StreamingServerApi streamingServerApi) {
        Intrinsics.checkNotNullParameter(stremioApi, "stremioApi");
        Intrinsics.checkNotNullParameter(streamingServerApi, "streamingServerApi");
        this.stremioApi = stremioApi;
        this.streamingServerApi = streamingServerApi;
        this.playerTimeChangedThrottle = new Throttle(C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
        MutableStateFlow<PlayerState> MutableStateFlow = StateFlowKt.MutableStateFlow(new PlayerState(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, false, 0L, null, null, 4194303, null));
        this._state = MutableStateFlow;
        this.state = FlowKt.asStateFlow(MutableStateFlow);
        byte[] stateNative = Core.INSTANCE.getStateNative(Field.CTX.INSTANCE);
        Object companionObjectInstance = KClasses.getCompanionObjectInstance(Reflection.getOrCreateKotlinClass(Ctx.class));
        Intrinsics.checkNotNull(companionObjectInstance, "null cannot be cast to non-null type pbandk.Message.Companion<T of com.stremio.core.Core.getState>");
        Profile profile = ((Ctx) MessageKt.decodeFromByteArray((Message.Companion) companionObjectInstance, stateNative)).getProfile();
        this.profile = profile;
        this.settings = profile.getSettings();
    }

    public final StateFlow<PlayerState> getState() {
        return this.state;
    }

    public final Profile.Settings getSettings() {
        return this.settings;
    }

    public final void setSettings(Profile.Settings settings) {
        Intrinsics.checkNotNullParameter(settings, "<set-?>");
        this.settings = settings;
    }

    public final void onStateChange(VideoPlaybackState playbackState) {
        Intrinsics.checkNotNullParameter(playbackState, "playbackState");
        if (this.state.getValue().isLoading()) {
            this._state.setValue(PlayerState.copy$default(this.state.getValue(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, false, 0L, null, null, 4063231, null));
        }
        switch (PlayerViewModel$$ExternalSyntheticTypeSwitchClass1.switchDispatch$5(playbackState, 0)) {
            case 0:
                this._state.setValue(PlayerState.copy$default(this.state.getValue(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, false, 0L, null, null, 3932159, null));
                this.stremioApi.playerPausedChanged(false);
                return;
            case 1:
                this.stremioApi.playerPausedChanged(true);
                return;
            case 2:
                this.stremioApi.playerEnded();
                return;
            case 3:
                this.stremioApi.playerNextVideo();
                return;
            case 4:
                this._state.setValue(PlayerState.copy$default(this.state.getValue(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, ((VideoPlaybackState.Error) playbackState).getException(), false, true, 0L, null, null, 3866623, null));
                return;
            case 5:
                VideoPlaybackState.Seek seek = (VideoPlaybackState.Seek) playbackState;
                this.stremioApi.playerSeek(seek.getPosition(), seek.getDuration());
                return;
            case 6:
                VideoPlaybackState.UpdateProgress updateProgress = (VideoPlaybackState.UpdateProgress) playbackState;
                final long position = updateProgress.getPosition();
                final long duration = updateProgress.getDuration();
                if (position <= 0 || duration <= 0) {
                    return;
                }
                this.playerTimeChangedThrottle.run(new Function0() { // from class: com.stremio.common.viewmodels.PlayerViewModel$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PlayerViewModel.onStateChange$lambda$0(this.f$0, position, duration);
                    }
                });
                return;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    static final Unit onStateChange$lambda$0(PlayerViewModel playerViewModel, long j, long j2) {
        playerViewModel.stremioApi.playerTimeChanged(j, j2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.stremio.common.viewmodels.PlayerViewModel$load$1, reason: invalid class name */
    /* JADX INFO: compiled from: PlayerViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.common.viewmodels.PlayerViewModel$load$1", f = "PlayerViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $id;
        final /* synthetic */ String $metaAddonUrl;
        final /* synthetic */ String $streamAddonUrl;
        final /* synthetic */ String $streamData;
        final /* synthetic */ String $type;
        final /* synthetic */ String $videoId;
        int label;
        final /* synthetic */ PlayerViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, PlayerViewModel playerViewModel, String str2, String str3, String str4, String str5, String str6, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$streamData = str;
            this.this$0 = playerViewModel;
            this.$streamAddonUrl = str2;
            this.$metaAddonUrl = str3;
            this.$type = str4;
            this.$id = str5;
            this.$videoId = str6;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$streamData, this.this$0, this.$streamAddonUrl, this.$metaAddonUrl, this.$type, this.$id, this.$videoId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Stream streamDecodeStreamData = Core.INSTANCE.decodeStreamData(this.$streamData);
            PlayerViewModel playerViewModel = this.this$0;
            if (streamDecodeStreamData != null) {
                playerViewModel.loadPlayerJob = FlowKt.launchIn(FlowKt.onEach(new PlayerViewModel$load$1$invokeSuspend$$inlined$filter$1(playerViewModel.stremioApi.loadPlayer(streamDecodeStreamData, this.$streamAddonUrl, this.$metaAddonUrl, this.$type, this.$id, this.$videoId)), new AnonymousClass2(this.this$0, null)), ViewModelKt.getViewModelScope(this.this$0));
                return Unit.INSTANCE;
            }
            playerViewModel.onStateChange(new VideoPlaybackState.Error("Failed decoding stream data"));
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.stremio.common.viewmodels.PlayerViewModel$load$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: PlayerViewModel.kt */
        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/stremio/core/models/Player;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
        @DebugMetadata(c = "com.stremio.common.viewmodels.PlayerViewModel$load$1$2", f = "PlayerViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
        static final class AnonymousClass2 extends SuspendLambda implements Function2<Player, Continuation<? super Unit>, Object> {
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ PlayerViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(PlayerViewModel playerViewModel, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.this$0 = playerViewModel;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, continuation);
                anonymousClass2.L$0 = obj;
                return anonymousClass2;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Player player, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(player, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Player player = (Player) this.L$0;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.this$0.updatePlayerState(player);
                return Unit.INSTANCE;
            }
        }
    }

    public final void load(String streamData, String streamAddonUrl, String metaAddonUrl, String type, String id, String videoId) {
        Intrinsics.checkNotNullParameter(streamData, "streamData");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(streamData, this, streamAddonUrl, metaAddonUrl, type, id, videoId, null), 3, null);
    }

    public final void markVideoAsWatched(Video video, boolean watched) {
        Intrinsics.checkNotNullParameter(video, "video");
        this.stremioApi.playerMarkVideoAsWatched(video, watched);
    }

    public final void markSeasonAsWatched(Video video, boolean watched) {
        Intrinsics.checkNotNullParameter(video, "video");
        Video.SeriesInfo seriesInfo = video.getSeriesInfo();
        if (seriesInfo != null) {
            this.stremioApi.playerMarkSeasonAsWatched((int) seriesInfo.getSeason(), watched);
        }
    }

    public final void markCurrentVideoAsWatched() {
        Video currentVideo = this.state.getValue().getCurrentVideo();
        if (currentVideo != null) {
            this.stremioApi.playerMarkVideoAsWatched(currentVideo, true);
        }
    }

    public final Uri proxiedSubtitlesUri(Subtitle subtitle) {
        Intrinsics.checkNotNullParameter(subtitle, "subtitle");
        return this.streamingServerApi.proxiedSubtitlesUri(this.settings.getStreamingServerUrl(), subtitle);
    }

    /* JADX INFO: renamed from: com.stremio.common.viewmodels.PlayerViewModel$resolveTorrentExternalUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: PlayerViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.common.viewmodels.PlayerViewModel$resolveTorrentExternalUrl$1", f = "PlayerViewModel.kt", i = {}, l = {144}, m = "invokeSuspend", n = {}, nl = {147}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<String, Unit> $action;
        final /* synthetic */ Stream $stream;
        int label;
        final /* synthetic */ PlayerViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Stream stream, PlayerViewModel playerViewModel, Function1<? super String, Unit> function1, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$stream = stream;
            this.this$0 = playerViewModel;
            this.$action = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$stream, this.this$0, this.$action, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:20:0x005f  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            String streaming;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                int iSwitchDispatch$6 = PlayerViewModel$$ExternalSyntheticTypeSwitchClass1.switchDispatch$6(this.$stream.getSource(), 0);
                if (iSwitchDispatch$6 == 0) {
                    this.label = 1;
                    obj = this.this$0.streamingServerApi.proxiedTorrentExternalUrl(this.this$0.getSettings().getStreamingServerUrl(), this.$stream, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    streaming = (iSwitchDispatch$6 == 1 || iSwitchDispatch$6 == 2) ? this.$stream.getDeepLinks().getExternalPlayer().getStreaming() : null;
                }
                if (streaming != null) {
                    this.$action.invoke(streaming);
                }
                return Unit.INSTANCE;
            }
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            streaming = (String) obj;
            if (streaming != null) {
                this.$action.invoke(streaming);
            }
            return Unit.INSTANCE;
        }
    }

    public final void resolveTorrentExternalUrl(Function1<? super String, Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        Stream stream = this.state.getValue().getStream();
        if (stream == null) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(stream, this, action, null), 3, null);
    }

    public final void updatePlayer(PlayerType playerType) {
        Intrinsics.checkNotNullParameter(playerType, "playerType");
        this._state.setValue(PlayerState.copy$default(this.state.getValue(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, false, 0L, playerType, null, 3145727, null));
    }

    public final void updateInitialPosition(Long initialPosition) {
        this._state.setValue(PlayerState.copy$default(this.state.getValue(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, false, initialPosition != null ? initialPosition.longValue() : this.state.getValue().getInitialPosition(), null, null, 3670015, null));
    }

    public final void updateStreamState(Function1<? super Player.StreamState, Player.StreamState> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        Player.StreamState streamState = this.state.getValue().getStreamState();
        if (streamState == null) {
            streamState = new Player.StreamState(null, null, null, null, null, null, null, null, null, FrameMetricsAggregator.EVERY_DURATION, null);
        }
        Player.StreamState streamStateInvoke = action.invoke(streamState);
        this._state.setValue(PlayerState.copy$default(this.state.getValue(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, false, 0L, null, streamStateInvoke, 2097151, null));
        this.stremioApi.playerStreamStateChanged(streamStateInvoke);
    }

    public final void startMediaLoadJobs() {
        String streaming;
        Stream stream = this.state.getValue().getStream();
        if (stream == null) {
            return;
        }
        if (((stream.getSource() instanceof Stream.Source.Tramvai) || (stream.getSource() instanceof Stream.Source.Url)) && (streaming = stream.getDeepLinks().getExternalPlayer().getStreaming()) != null) {
            loadMediaInfo(streaming);
            loadVideoParams(streaming);
        }
    }

    private final void loadMediaInfo(String streamUrl) {
        this._state.setValue(PlayerState.copy$default(this.state.getValue(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, false, 0L, null, null, 4161535, null));
        boolean zAreEqual = Intrinsics.areEqual(this.settings.getFrameRateMatchingStrategy(), Profile.FrameRateMatchingStrategy.DISABLED.INSTANCE);
        if (this.loadMediaInfoJob != null || zAreEqual) {
            return;
        }
        this._state.setValue(PlayerState.copy$default(this.state.getValue(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, MediaInfoResult.Loading.INSTANCE, null, false, false, 0L, null, null, 4161535, null));
        this.loadMediaInfoJob = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getDefault(), null, new AnonymousClass1(streamUrl, this, null), 2, null);
    }

    /* JADX INFO: renamed from: com.stremio.common.viewmodels.PlayerViewModel$loadMediaInfo$1, reason: invalid class name */
    /* JADX INFO: compiled from: PlayerViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.common.viewmodels.PlayerViewModel$loadMediaInfo$1", f = "PlayerViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $streamUrl;
        int label;
        final /* synthetic */ PlayerViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, PlayerViewModel playerViewModel, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$streamUrl = str;
            this.this$0 = playerViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$streamUrl, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.this$0._state.setValue(PlayerState.copy$default(this.this$0.getState().getValue(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, MediaInfoLoader.INSTANCE.loadMediaInfo(this.$streamUrl), null, false, false, 0L, null, null, 4161535, null));
            return Unit.INSTANCE;
        }
    }

    private final void loadVideoParams(String streamUrl) {
        if (this.loadVideoParamsJob != null) {
            return;
        }
        this.loadVideoParamsJob = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getDefault(), null, new AnonymousClass1(streamUrl, null), 2, null);
    }

    /* JADX INFO: renamed from: com.stremio.common.viewmodels.PlayerViewModel$loadVideoParams$1, reason: invalid class name */
    /* JADX INFO: compiled from: PlayerViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.common.viewmodels.PlayerViewModel$loadVideoParams$1", f = "PlayerViewModel.kt", i = {}, l = {197, 198}, m = "invokeSuspend", n = {}, nl = {198, 197}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $streamUrl;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$streamUrl = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PlayerViewModel.this.new AnonymousClass1(this.$streamUrl, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:19:0x006d  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Player.VideoParams videoParams;
            Job job;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i == 1) {
                    ResultKt.throwOnFailure(obj);
                } else {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    Player.VideoParams videoParams2 = (Player.VideoParams) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    videoParams = videoParams2;
                }
                Player.VideoParams videoParamsCopy$default = Player.VideoParams.copy$default(videoParams, null, null, (String) obj, null, 11, null);
                job = PlayerViewModel.this.loadPlayerJob;
                if (job != null) {
                    Job.cancel$default(job, (CancellationException) null, 1, (Object) null);
                }
                PlayerViewModel playerViewModel = PlayerViewModel.this;
                playerViewModel.loadPlayerJob = FlowKt.launchIn(FlowKt.onEach(new PlayerViewModel$loadVideoParams$1$invokeSuspend$$inlined$filter$1(playerViewModel.stremioApi.loadPlayerSubtitles(videoParamsCopy$default)), new AnonymousClass2(PlayerViewModel.this, null)), ViewModelKt.getViewModelScope(PlayerViewModel.this));
                return Unit.INSTANCE;
            }
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = PlayerViewModel.this.streamingServerApi.videoParams(PlayerViewModel.this.getSettings().getStreamingServerUrl(), this.$streamUrl, this);
            if (obj != coroutine_suspended) {
            }
            return coroutine_suspended;
            Player.VideoParams videoParams3 = (Player.VideoParams) obj;
            this.L$0 = videoParams3;
            this.label = 2;
            Object objResolveFilename = PlayerViewModel.this.resolveFilename(this);
            if (objResolveFilename != coroutine_suspended) {
                videoParams = videoParams3;
                obj = objResolveFilename;
                Player.VideoParams videoParamsCopy$default2 = Player.VideoParams.copy$default(videoParams, null, null, (String) obj, null, 11, null);
                job = PlayerViewModel.this.loadPlayerJob;
                if (job != null) {
                    Job.cancel$default(job, (CancellationException) null, 1, (Object) null);
                }
                PlayerViewModel playerViewModel2 = PlayerViewModel.this;
                playerViewModel2.loadPlayerJob = FlowKt.launchIn(FlowKt.onEach(new PlayerViewModel$loadVideoParams$1$invokeSuspend$$inlined$filter$1(playerViewModel2.stremioApi.loadPlayerSubtitles(videoParamsCopy$default2)), new AnonymousClass2(PlayerViewModel.this, null)), ViewModelKt.getViewModelScope(PlayerViewModel.this));
                return Unit.INSTANCE;
            }
            return coroutine_suspended;
        }

        /* JADX INFO: renamed from: com.stremio.common.viewmodels.PlayerViewModel$loadVideoParams$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: PlayerViewModel.kt */
        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/stremio/core/models/Player;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
        @DebugMetadata(c = "com.stremio.common.viewmodels.PlayerViewModel$loadVideoParams$1$2", f = "PlayerViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
        static final class AnonymousClass2 extends SuspendLambda implements Function2<Player, Continuation<? super Unit>, Object> {
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ PlayerViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(PlayerViewModel playerViewModel, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.this$0 = playerViewModel;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, continuation);
                anonymousClass2.L$0 = obj;
                return anonymousClass2;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Player player, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(player, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Player player = (Player) this.L$0;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.this$0.updatePlayerState(player);
                return Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object resolveFilename(Continuation<? super String> continuation) {
        AnonymousClass1 anonymousClass1;
        Stream.Url url;
        String url2;
        List listSplit$default;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object objStatistics = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objStatistics);
            Stream stream = this.state.getValue().getStream();
            if (stream == null) {
                return null;
            }
            String filename = stream.getBehaviorHints().getFilename();
            if (filename != null) {
                return filename;
            }
            int iSwitchDispatch$4 = PlayerViewModel$$ExternalSyntheticTypeSwitchClass1.switchDispatch$4(stream.getSource(), 0);
            if (iSwitchDispatch$4 != 0) {
                if (iSwitchDispatch$4 != 1 || (url = stream.getUrl()) == null || (url2 = url.getUrl()) == null || (listSplit$default = StringsKt.split$default((CharSequence) url2, new char[]{FileSystemKt.UnixPathSeparator}, false, 0, 6, (Object) null)) == null) {
                    return null;
                }
                return (String) CollectionsKt.lastOrNull(listSplit$default);
            }
            StreamingServerApi streamingServerApi = this.streamingServerApi;
            String streamingServerUrl = this.settings.getStreamingServerUrl();
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(stream);
            anonymousClass1.label = 1;
            objStatistics = streamingServerApi.statistics(streamingServerUrl, stream, anonymousClass1);
            if (objStatistics == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objStatistics);
        }
        StreamingServerApi.StreamStatistics streamStatistics = (StreamingServerApi.StreamStatistics) objStatistics;
        if (streamStatistics != null) {
            return streamStatistics.getStreamName();
        }
        return null;
    }

    public final void updateChapters(List<Chapter> chapters) {
        Intrinsics.checkNotNullParameter(chapters, "chapters");
        Pair pairUpdateSkipSegments$default = updateSkipSegments$default(this, chapters, null, 2, null);
        this._state.setValue(PlayerState.copy$default(this.state.getValue(), null, null, null, null, null, null, null, null, null, null, (SkipSegment) pairUpdateSkipSegments$default.component1(), (SkipSegment) pairUpdateSkipSegments$default.component2(), chapters, null, null, null, null, false, false, 0L, null, null, 4187135, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updatePlayerState(Player player) {
        ArrayList arrayListEmptyList;
        LibraryItemState state;
        PlayerType playerType;
        List<Video> videos;
        Video.SeriesInfo seriesInfo;
        List<Video> videos2;
        List<Video> videos3;
        List<Video> videos4;
        List<Video> videos5;
        ResourceRequest streamRequest;
        ResourcePath path;
        Stream stream = this.state.getValue().getStream();
        Long lValueOf = null;
        if (stream == null) {
            LoadableConvertedStream stream2 = player.getStream();
            stream = stream2 != null ? stream2.getReady() : null;
            if (stream == null) {
                return;
            }
        }
        Stream stream3 = stream;
        MetaItem asReady = LoadableExtKt.getAsReady(player.getMetaItem());
        LibraryItem libraryItem = player.getLibraryItem();
        Video.SeriesInfo seriesInfo2 = player.getSeriesInfo();
        Player.Selected selected = player.getSelected();
        String id = (selected == null || (streamRequest = selected.getStreamRequest()) == null || (path = streamRequest.getPath()) == null) ? null : path.getId();
        int i = -1;
        if (asReady != null && (videos5 = asReady.getVideos()) != null) {
            Iterator<Video> it = videos5.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                if (Intrinsics.areEqual(it.next().getId(), id)) {
                    i = i2;
                    break;
                }
                i2++;
            }
        }
        Video video = (asReady == null || (videos4 = asReady.getVideos()) == null) ? null : (Video) CollectionsKt.getOrNull(videos4, i);
        Video video2 = (asReady == null || (videos3 = asReady.getVideos()) == null) ? null : (Video) CollectionsKt.getOrNull(videos3, i - 1);
        Video video3 = (asReady == null || (videos2 = asReady.getVideos()) == null) ? null : (Video) CollectionsKt.getOrNull(videos2, i + 1);
        Player.IntroOutro introOutro = player.getIntroOutro();
        Pair pairUpdateSkipSegments$default = updateSkipSegments$default(this, null, introOutro, 1, null);
        SkipSegment skipSegment = (SkipSegment) pairUpdateSkipSegments$default.component1();
        SkipSegment skipSegment2 = (SkipSegment) pairUpdateSkipSegments$default.component2();
        Video nextVideo = player.getNextVideo();
        if (asReady == null || (videos = asReady.getVideos()) == null) {
            arrayListEmptyList = CollectionsKt.emptyList();
        } else {
            ArrayList arrayList = new ArrayList();
            Iterator it2 = videos.iterator();
            while (it2.hasNext()) {
                Object next = it2.next();
                Video.SeriesInfo seriesInfo3 = ((Video) next).getSeriesInfo();
                if (seriesInfo3 != null) {
                    lValueOf = Long.valueOf(seriesInfo3.getSeason());
                }
                if (Intrinsics.areEqual(lValueOf, (video == null || (seriesInfo = video.getSeriesInfo()) == null) ? null : Long.valueOf(seriesInfo.getSeason()))) {
                    arrayList.add(next);
                }
                it2 = it2;
                lValueOf = null;
            }
            arrayListEmptyList = arrayList;
        }
        List list = arrayListEmptyList;
        List<Subtitle> subtitles = stream3.getSubtitles();
        List<LoadableSubtitles> subtitles2 = player.getSubtitles();
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it3 = subtitles2.iterator();
        while (it3.hasNext()) {
            CollectionsKt.addAll(arrayList2, LoadableExtKt.getAsReady((LoadableSubtitles) it3.next()));
        }
        List listPlus = CollectionsKt.plus((Collection) subtitles, (Iterable) arrayList2);
        HashSet hashSet = new HashSet();
        ArrayList arrayList3 = new ArrayList();
        Iterator it4 = listPlus.iterator();
        while (it4.hasNext()) {
            Object next2 = it4.next();
            Iterator it5 = it4;
            if (hashSet.add(((Subtitle) next2).getId())) {
                arrayList3.add(next2);
            }
            it4 = it5;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator it6 = arrayList3.iterator();
        while (it6.hasNext()) {
            Object next3 = it6.next();
            Subtitle subtitle = (Subtitle) next3;
            Iterator it7 = it6;
            Locale locale = SettingsViewModel.INSTANCE.getLOCALE_OVERRIDES().get(subtitle.getLang());
            if (locale == null) {
                locale = new Locale(subtitle.getLang());
            }
            Object obj = linkedHashMap.get(locale);
            if (obj == null) {
                obj = (List) new ArrayList();
                linkedHashMap.put(locale, obj);
            }
            ((List) obj).add(next3);
            it6 = it7;
        }
        SortedMap sortedMap = MapsKt.toSortedMap(linkedHashMap, subtitlesLocaleComparator());
        long timeOffset = 0;
        if ((video == null || video.getCurrentVideo()) && libraryItem != null && (state = libraryItem.getState()) != null) {
            timeOffset = state.getTimeOffset();
        }
        long j = timeOffset;
        Player.StreamState streamState = this.state.getValue().getStreamState();
        if (streamState == null) {
            streamState = player.getStreamState();
        }
        Player.StreamState streamState2 = streamState;
        PlayerType playerTypeFrom = PlayerType.INSTANCE.from(this.settings.getPlayerType());
        PlayerType playerTypeFrom2 = playerTypeFrom == PlayerType.EXTERNAL ? null : PlayerType.INSTANCE.from(streamState2 != null ? streamState2.getPlayerType() : null);
        if (playerTypeFrom2 == null) {
            if (playerTypeFrom == null) {
                playerTypeFrom = this.state.getValue().getPlayerType();
            }
            playerType = playerTypeFrom;
        } else {
            playerType = playerTypeFrom2;
        }
        this._state.setValue(PlayerState.copy$default(this.state.getValue(), player.getSelected(), stream3, asReady, libraryItem, seriesInfo2, video, video2, video3, nextVideo, introOutro, skipSegment, skipSegment2, null, list, sortedMap, null, null, false, false, j, playerType, streamState2, 495616, null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Pair updateSkipSegments$default(PlayerViewModel playerViewModel, List list, Player.IntroOutro introOutro, int i, Object obj) {
        if ((i & 1) != 0) {
            list = playerViewModel.state.getValue().getChapters();
        }
        if ((i & 2) != 0) {
            introOutro = playerViewModel.state.getValue().getIntroOutro();
        }
        return playerViewModel.updateSkipSegments(list, introOutro);
    }

    private final Pair<SkipSegment, SkipSegment> updateSkipSegments(List<Chapter> chapters, Player.IntroOutro introOutro) {
        SkipSegment skipSegment;
        Object next;
        Long outro;
        Player.IntroData intro;
        Auth auth = this.profile.getAuth();
        SkipSegment skipSegment2 = null;
        if (!ProfileExtKt.isPremium(auth != null ? auth.getUser() : null)) {
            return new Pair<>(null, null);
        }
        Iterator<Chapter> it = chapters.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            if (it.next().getType() == ChapterType.INTRO) {
                break;
            }
            i++;
        }
        Chapter chapter = (Chapter) CollectionsKt.getOrNull(chapters, i);
        Chapter chapter2 = (Chapter) CollectionsKt.getOrNull(chapters, i + 1);
        if (chapter == null) {
            skipSegment = null;
        } else if (chapter.getEndTime() <= chapter.getStartTime() && chapter2 != null) {
            skipSegment = new SkipSegment(chapter.getStartTime(), chapter2.getStartTime());
        } else {
            skipSegment = new SkipSegment(chapter.getStartTime(), chapter.getEndTime());
        }
        Iterator<T> it2 = chapters.iterator();
        do {
            if (!it2.hasNext()) {
                next = null;
                break;
            }
            next = it2.next();
        } while (((Chapter) next).getType() != ChapterType.OUTRO);
        Chapter chapter3 = (Chapter) next;
        SkipSegment skipSegment3 = chapter3 != null ? new SkipSegment(chapter3.getStartTime(), chapter3.getEndTime()) : null;
        if (skipSegment == null) {
            skipSegment = (introOutro == null || (intro = introOutro.getIntro()) == null) ? null : new SkipSegment(intro.getFrom(), intro.getTo());
        }
        if (skipSegment3 != null) {
            skipSegment2 = skipSegment3;
        } else if (introOutro != null && (outro = introOutro.getOutro()) != null) {
            skipSegment2 = new SkipSegment(outro.longValue(), 0L, 2, null);
        }
        return new Pair<>(skipSegment, skipSegment2);
    }

    public final void unload() {
        this.stremioApi.clearState(Field.PLAYER.INSTANCE);
    }

    private final Comparator<Locale> subtitlesLocaleComparator() {
        String subtitlesLanguage = this.settings.getSubtitlesLanguage();
        final String strNormalizeLanguageCode = subtitlesLanguage != null ? PlatformKt.normalizeLanguageCode(subtitlesLanguage) : null;
        String secondarySubtitlesLanguage = this.settings.getSecondarySubtitlesLanguage();
        final String strNormalizeLanguageCode2 = secondarySubtitlesLanguage != null ? PlatformKt.normalizeLanguageCode(secondarySubtitlesLanguage) : null;
        final Comparator comparator = new Comparator() { // from class: com.stremio.common.viewmodels.PlayerViewModel$subtitlesLocaleComparator$$inlined$compareByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                String languageTag = ((Locale) t2).toLanguageTag();
                Intrinsics.checkNotNullExpressionValue(languageTag, "toLanguageTag(...)");
                Boolean boolValueOf = Boolean.valueOf(Intrinsics.areEqual(PlatformKt.normalizeLanguageCode(languageTag), strNormalizeLanguageCode));
                String languageTag2 = ((Locale) t).toLanguageTag();
                Intrinsics.checkNotNullExpressionValue(languageTag2, "toLanguageTag(...)");
                return ComparisonsKt.compareValues(boolValueOf, Boolean.valueOf(Intrinsics.areEqual(PlatformKt.normalizeLanguageCode(languageTag2), strNormalizeLanguageCode)));
            }
        };
        final Comparator comparator2 = new Comparator() { // from class: com.stremio.common.viewmodels.PlayerViewModel$subtitlesLocaleComparator$$inlined$thenByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int iCompare = comparator.compare(t, t2);
                if (iCompare != 0) {
                    return iCompare;
                }
                String languageTag = ((Locale) t2).toLanguageTag();
                Intrinsics.checkNotNullExpressionValue(languageTag, "toLanguageTag(...)");
                Boolean boolValueOf = Boolean.valueOf(Intrinsics.areEqual(PlatformKt.normalizeLanguageCode(languageTag), strNormalizeLanguageCode2));
                String languageTag2 = ((Locale) t).toLanguageTag();
                Intrinsics.checkNotNullExpressionValue(languageTag2, "toLanguageTag(...)");
                return ComparisonsKt.compareValues(boolValueOf, Boolean.valueOf(Intrinsics.areEqual(PlatformKt.normalizeLanguageCode(languageTag2), strNormalizeLanguageCode2)));
            }
        };
        return new Comparator() { // from class: com.stremio.common.viewmodels.PlayerViewModel$subtitlesLocaleComparator$$inlined$thenBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int iCompare = comparator2.compare(t, t2);
                return iCompare != 0 ? iCompare : ComparisonsKt.compareValues(((Locale) t).toLanguageTag(), ((Locale) t2).toLanguageTag());
            }
        };
    }

    public /* synthetic */ PlayerViewModel(StremioApi stremioApi, StreamingServerApi streamingServerApi, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (StremioApi) KoinPlatformTools.INSTANCE.defaultContext().get().getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(StremioApi.class), null, null) : stremioApi, (i & 2) != 0 ? (StreamingServerApi) KoinPlatformTools.INSTANCE.defaultContext().get().getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(StreamingServerApi.class), null, null) : streamingServerApi);
    }
}