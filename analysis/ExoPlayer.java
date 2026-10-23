package com.stremio.common.players;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.SurfaceView;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.ForwardingPlayer;
import androidx.media3.common.Label;
import androidx.media3.common.MediaItem;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionOverride;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.text.Cue;
import androidx.media3.common.text.CueGroup;
import androidx.media3.datasource.DefaultDataSource;
import androidx.media3.datasource.DefaultHttpDataSource;
import androidx.media3.exoplayer.DefaultLoadControl;
import androidx.media3.exoplayer.DefaultRenderersFactory;
import androidx.media3.exoplayer.LoadControl;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.extractor.DefaultExtractorsFactory;
import androidx.media3.ui.PlayerView;
import androidx.media3.ui.SubtitleView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.collect.ImmutableList;
import com.stremio.common.extensions.DownloadInfoExtKt;
import com.stremio.common.extensions.PlayerExtKt;
import com.stremio.common.players.subtitles.CustomRenderersFactory;
import com.stremio.common.players.subtitles.CustomTextRenderer;
import com.stremio.core.types.profile.Profile;
import com.stremio.core.types.resource.Stream;
import io.github.peerless2012.ass.media.kt.AssPlayerKt;
import io.github.peerless2012.ass.media.type.AssRenderType;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.IntIterator;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import org.koin.mp.KoinPlatformTools;

/* JADX INFO: compiled from: ExoPlayer.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000Î\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0002\u0010\u0019\b\u0007\u0018\u0000 \\2\u00020\u00012\u00020\u0002:\u0001\\B)\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0012\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\"\u001a\u00020 H\u0016J\u0010\u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020%H\u0016J\u0010\u0010&\u001a\u00020 2\u0006\u0010$\u001a\u00020%H\u0016J\u0010\u0010'\u001a\u00020 2\u0006\u0010$\u001a\u00020%H\u0016J\u0018\u0010(\u001a\u00020 2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0016J\u0010\u0010-\u001a\u00020 2\u0006\u0010.\u001a\u00020,H\u0016J\b\u0010/\u001a\u00020,H\u0016J\b\u00100\u001a\u00020,H\u0016J\u0010\u00101\u001a\u00020 2\u0006\u00102\u001a\u000203H\u0016J\b\u00104\u001a\u000203H\u0016J\u0012\u00105\u001a\u00020 2\b\u00106\u001a\u0004\u0018\u000107H\u0016J\u0010\u00108\u001a\u00020 2\u0006\u00109\u001a\u00020,H\u0016J\u0010\u0010:\u001a\u00020 2\u0006\u00109\u001a\u00020,H\u0016J\u0010\u0010;\u001a\u00020 2\u0006\u0010<\u001a\u000203H\u0016J\u0010\u0010=\u001a\u00020 2\u0006\u0010>\u001a\u000203H\u0016J(\u0010?\u001a\u00020 2\f\u0010@\u001a\b\u0012\u0004\u0012\u0002070AH\u0017b\u0010\bB\u0012\f\bC\u0012\b\b\fJ\u0004\b\b(DJ\u0010\u0010E\u001a\u00020 2\u0006\u0010F\u001a\u00020\u000eH\u0016J\n\u0010G\u001a\u0004\u0018\u00010HH\u0002J\b\u0010I\u001a\u00020JH\u0002J\r\u0010\u000f\u001a\u00020\u0010H\u0002¢\u0006\u0002\u0010KJ&\u0010L\u001a\b\u0012\u0004\u0012\u0002070A2\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020RH\u0002J\u001c\u0010S\u001a\u00020T*\u00020U2\u0006\u0010O\u001a\u00020P2\u0006\u0010V\u001a\u00020PH\u0002J\u0016\u0010W\u001a\b\u0012\u0004\u0012\u00020X0A2\u0006\u0010M\u001a\u00020NH\u0002J\u001a\u0010Y\u001a\u0004\u0018\u00010Z2\u0006\u0010[\u001a\u00020P2\u0006\u00106\u001a\u000207H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eÊ\u0001\f\b^\u0012\b\b_\u0012\u0004\b\u0003\u0010\u0000¨\u0006]"}, d2 = {"Lcom/stremio/common/players/ExoPlayer;", "Landroidx/media3/common/ForwardingPlayer;", "Lcom/stremio/common/players/Player;", "context", "Landroid/content/Context;", "stream", "Lcom/stremio/core/types/resource/Stream;", "settings", "Lcom/stremio/core/types/profile/Profile$Settings;", "loadControl", "Landroidx/media3/exoplayer/LoadControl;", "<init>", "(Landroid/content/Context;Lcom/stremio/core/types/resource/Stream;Lcom/stremio/core/types/profile/Profile$Settings;Landroidx/media3/exoplayer/LoadControl;)V", "videoScalingMode", "Lcom/stremio/common/players/VideoScale;", "eventListener", "com/stremio/common/players/ExoPlayer$eventListener$1", "Lcom/stremio/common/players/ExoPlayer$eventListener$1;", "playerListener", "Lcom/stremio/common/players/PlayerListener;", "playerView", "Landroidx/media3/ui/PlayerView;", "handler", "Landroid/os/Handler;", "updateRunnable", "com/stremio/common/players/ExoPlayer$updateRunnable$1", "Lcom/stremio/common/players/ExoPlayer$updateRunnable$1;", "capabilities", "Lcom/stremio/common/players/PlayerCapabilities;", "getCapabilities", "()Lcom/stremio/common/players/PlayerCapabilities;", "prepare", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "release", "attachView", "view", "Landroid/view/View;", "detachView", "updateView", "load", "uri", "Landroid/net/Uri;", "startTime", "", "setTime", "time", "getTime", "getBufferedTime", "setPlaybackRate", "rate", "", "getPlaybackRate", "setTrack", "track", "Lcom/stremio/common/players/Track;", "setAudioTrackDelay", "delay", "setTextTrackDelay", "setTextTrackOffset", TypedValues.CycleType.S_WAVE_OFFSET, "setTextTrackSize", ContentDisposition.Parameters.Size, "addExternalTextTracks", "tracks", "", "Landroid/annotation/SuppressLint;", "value", "WrongConstant", "setVideoScale", "scale", "getTextRenderer", "Lcom/stremio/common/players/subtitles/CustomTextRenderer;", "wrappedExoPlayer", "Landroidx/media3/exoplayer/ExoPlayer;", "()Lcom/stremio/common/players/ExoPlayer$eventListener$1;", "toTracks", "group", "Landroidx/media3/common/Tracks$Group;", "groupIndex", "", "trackType", "Lcom/stremio/common/players/TrackType;", "toTrackId", "", "Landroidx/media3/common/Format;", "trackIndex", "toChapters", "Lcom/stremio/common/players/Chapter;", "findTrackOverride", "Landroidx/media3/common/TrackSelectionOverride;", "type", "Companion", "Stremio:common_release", "Landroidx/compose/runtime/internal/StabilityInferred;", "parameters"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ExoPlayer extends ForwardingPlayer implements Player {
    private static final int DEFAULT_CONNECT_READ_TIMEOUT;
    private static final String EXOPLAYER_LIBASS_PREF_KEY = "prefs_experimental_libass";
    private static final String LRM = "\u200e";
    private static final Regex NEW_LINE_REGEX;
    private static final int ROLE_FLAG_EXTERNAL = 1048576;
    private static final int TORRENT_CONNECT_READ_TIMEOUT;
    private final PlayerCapabilities capabilities;
    private final Context context;
    private final AnonymousClass1 eventListener;
    private final Handler handler;
    private final LoadControl loadControl;
    private PlayerListener playerListener;
    private PlayerView playerView;
    private final Profile.Settings settings;
    private final Stream stream;
    private final ExoPlayer$updateRunnable$1 updateRunnable;
    private VideoScale videoScalingMode;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ExoPlayer.kt */
    @Metadata(k = 3, mv = {2, 4, 0}, xi = 1328)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[VideoScale.values().length];
            try {
                iArr[VideoScale.CROP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[VideoScale.STRETCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // com.stremio.common.players.Player
    public void setAudioTrackDelay(long delay) {
    }

    @Override // com.stremio.common.players.Player
    public void updateView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
    }

    public /* synthetic */ ExoPlayer(Context context, Stream stream, Profile.Settings settings, DefaultLoadControl defaultLoadControl, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, stream, settings, (i & 8) != 0 ? new DefaultLoadControl() : defaultLoadControl);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r11v4, types: [com.stremio.common.players.ExoPlayer$updateRunnable$1] */
    public ExoPlayer(Context context, Stream stream, Profile.Settings settings, LoadControl loadControl) {
        super(INSTANCE.createPlayer(context, stream, settings, loadControl));
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(stream, "stream");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(loadControl, "loadControl");
        this.context = context;
        this.stream = stream;
        this.settings = settings;
        this.loadControl = loadControl;
        this.videoScalingMode = VideoScale.FIT;
        this.eventListener = eventListener();
        this.handler = new Handler(Looper.getMainLooper());
        this.updateRunnable = new Runnable() { // from class: com.stremio.common.players.ExoPlayer$updateRunnable$1
            @Override // java.lang.Runnable
            public void run() {
                PlayerListener playerListener;
                if (this.this$0.isPlaying() && (playerListener = this.this$0.playerListener) != null) {
                    playerListener.onTimeChanged(this.this$0.getCurrentPosition(), this.this$0.getBufferedPosition(), this.this$0.getDuration());
                }
                this.this$0.handler.postDelayed(this, 500L);
            }
        };
        IntProgression intProgressionStep = RangesKt.step(new IntRange(25, 250), 25);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intProgressionStep, 10));
        Iterator<Integer> it = intProgressionStep.iterator();
        while (it.hasNext()) {
            arrayList.add(Float.valueOf(((IntIterator) it).nextInt() / 100.0f));
        }
        this.capabilities = new PlayerCapabilities(true, false, true, true, true, true, arrayList, true, CollectionsKt.listOf((Object[]) new VideoScale[]{VideoScale.FIT, VideoScale.CROP, VideoScale.STRETCH}));
    }

    /* JADX INFO: compiled from: ExoPlayer.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/stremio/common/players/ExoPlayer$Companion;", "", "<init>", "()V", "ROLE_FLAG_EXTERNAL", "", "DEFAULT_CONNECT_READ_TIMEOUT", "TORRENT_CONNECT_READ_TIMEOUT", "EXOPLAYER_LIBASS_PREF_KEY", "", "NEW_LINE_REGEX", "Lkotlin/text/Regex;", "LRM", "createPlayer", "Landroidx/media3/exoplayer/ExoPlayer;", "context", "Landroid/content/Context;", "stream", "Lcom/stremio/core/types/resource/Stream;", "settings", "Lcom/stremio/core/types/profile/Profile$Settings;", "loadControl", "Landroidx/media3/exoplayer/LoadControl;", "Stremio:common_release"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final androidx.media3.exoplayer.ExoPlayer createPlayer(Context context, Stream stream, Profile.Settings settings, LoadControl loadControl) {
            int i;
            androidx.media3.exoplayer.ExoPlayer exoPlayerBuild;
            boolean z = ((SharedPreferences) KoinPlatformTools.INSTANCE.defaultContext().get().getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(SharedPreferences.class), null, null)).getBoolean(ExoPlayer.EXOPLAYER_LIBASS_PREF_KEY, false);
            boolean hardwareDecoding = settings.getHardwareDecoding();
            if (hardwareDecoding) {
                i = 1;
            } else {
                if (hardwareDecoding) {
                    throw new NoWhenBranchMatchedException();
                }
                i = 2;
            }
            AudioAttributes audioAttributesBuild = new AudioAttributes.Builder().setUsage(1).setContentType(3).build();
            Intrinsics.checkNotNullExpressionValue(audioAttributesBuild, "build(...)");
            DefaultTrackSelector defaultTrackSelector = new DefaultTrackSelector(context);
            defaultTrackSelector.setParameters(defaultTrackSelector.buildUponParameters().setTrackTypeDisabled(3, true).setTrackTypeDisabled(1, true).setTunnelingEnabled(settings.getAudioPassthrough()).setAllowInvalidateSelectionsOnRendererCapabilitiesChange(true).build());
            int i2 = stream.getSource() instanceof Stream.Source.Tramvai ? ExoPlayer.TORRENT_CONNECT_READ_TIMEOUT : ExoPlayer.DEFAULT_CONNECT_READ_TIMEOUT;
            DefaultHttpDataSource.Factory readTimeoutMs = new DefaultHttpDataSource.Factory().setAllowCrossProtocolRedirects(true).setConnectTimeoutMs(i2).setReadTimeoutMs(i2);
            Intrinsics.checkNotNullExpressionValue(readTimeoutMs, "setReadTimeoutMs(...)");
            DefaultDataSource.Factory factory = new DefaultDataSource.Factory(context, readTimeoutMs);
            DefaultExtractorsFactory tsExtractorTimestampSearchBytes = new DefaultExtractorsFactory().setTsExtractorFlags(65).setTsExtractorTimestampSearchBytes(338400);
            Intrinsics.checkNotNullExpressionValue(tsExtractorTimestampSearchBytes, "setTsExtractorTimestampSearchBytes(...)");
            DefaultRenderersFactory defaultRenderersFactoryForceEnableMediaCodecAsynchronousQueueing = new CustomRenderersFactory(context).setEnableDecoderFallback(true).setExtensionRendererMode(i).forceEnableMediaCodecAsynchronousQueueing();
            Intrinsics.checkNotNullExpressionValue(defaultRenderersFactoryForceEnableMediaCodecAsynchronousQueueing, "forceEnableMediaCodecAsynchronousQueueing(...)");
            androidx.media3.exoplayer.ExoPlayer.Builder trackSelector = new androidx.media3.exoplayer.ExoPlayer.Builder(context, defaultRenderersFactoryForceEnableMediaCodecAsynchronousQueueing).setName("exo").setSeekParameters(SeekParameters.NEXT_SYNC).setLoadControl(loadControl).setAudioAttributes(audioAttributesBuild, true).setTrackSelector(defaultTrackSelector);
            if (z) {
                Intrinsics.checkNotNull(trackSelector);
                exoPlayerBuild = AssPlayerKt.buildWithAssSupport$default(trackSelector, context, AssRenderType.CUES, null, null, factory, tsExtractorTimestampSearchBytes, null, 68, null);
            } else {
                exoPlayerBuild = trackSelector.setMediaSourceFactory(new DefaultMediaSourceFactory(factory, tsExtractorTimestampSearchBytes)).build();
                Intrinsics.checkNotNull(exoPlayerBuild);
            }
            exoPlayerBuild.setVideoChangeFrameRateStrategy(Intrinsics.areEqual(settings.getFrameRateMatchingStrategy(), Profile.FrameRateMatchingStrategy.DISABLED.INSTANCE) ? 0 : Integer.MIN_VALUE);
            return exoPlayerBuild;
        }
    }

    static {
        Duration.Companion companion = Duration.INSTANCE;
        DEFAULT_CONNECT_READ_TIMEOUT = (int) Duration.m13286getInWholeMillisecondsimpl(DurationKt.toDuration(20, DurationUnit.SECONDS));
        Duration.Companion companion2 = Duration.INSTANCE;
        TORRENT_CONNECT_READ_TIMEOUT = (int) Duration.m13286getInWholeMillisecondsimpl(DurationKt.toDuration(2, DurationUnit.MINUTES));
        NEW_LINE_REGEX = new Regex("(^)", RegexOption.MULTILINE);
    }

    @Override // com.stremio.common.players.Player
    public PlayerCapabilities getCapabilities() {
        return this.capabilities;
    }

    @Override // com.stremio.common.players.Player
    public void prepare(PlayerListener listener) {
        this.handler.postDelayed(this.updateRunnable, 500L);
        addListener(this.eventListener);
        this.playerListener = listener;
        super.prepare();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void release() {
        stop();
        this.handler.removeCallbacks(this.updateRunnable);
        removeListener(this.eventListener);
        this.playerListener = null;
        super.release();
    }

    @Override // com.stremio.common.players.Player
    public void attachView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        PlayerView playerView = (PlayerView) view;
        this.playerView = playerView;
        if (playerView != null) {
            playerView.setPlayer(this);
        }
        PlayerView playerView2 = this.playerView;
        View videoSurfaceView = playerView2 != null ? playerView2.getVideoSurfaceView() : null;
        Intrinsics.checkNotNull(videoSurfaceView);
        setVideoSurfaceView(videoSurfaceView instanceof SurfaceView ? (SurfaceView) videoSurfaceView : null);
        PlayerView playerView3 = this.playerView;
        if (playerView3 != null) {
            playerView3.onResume();
        }
    }

    @Override // com.stremio.common.players.Player
    public void detachView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        PlayerView playerView = (PlayerView) view;
        this.playerView = playerView;
        View videoSurfaceView = playerView != null ? playerView.getVideoSurfaceView() : null;
        Intrinsics.checkNotNull(videoSurfaceView);
        clearVideoSurfaceView(videoSurfaceView instanceof SurfaceView ? (SurfaceView) videoSurfaceView : null);
        PlayerView playerView2 = this.playerView;
        if (playerView2 != null) {
            playerView2.setPlayer(null);
        }
        PlayerView playerView3 = this.playerView;
        if (playerView3 != null) {
            playerView3.onPause();
        }
    }

    @Override // com.stremio.common.players.Player
    public void load(Uri uri, long startTime) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        setPlayWhenReady(false);
        MediaItem mediaItemBuild = new MediaItem.Builder().setUri(uri).build();
        Intrinsics.checkNotNullExpressionValue(mediaItemBuild, "build(...)");
        setMediaItem(mediaItemBuild, startTime);
    }

    @Override // com.stremio.common.players.Player
    public void setTime(long time) {
        seekTo(time);
    }

    @Override // com.stremio.common.players.Player
    public long getTime() {
        return getCurrentPosition();
    }

    @Override // com.stremio.common.players.Player
    public long getBufferedTime() {
        return getBufferedPosition();
    }

    @Override // com.stremio.common.players.Player
    public void setPlaybackRate(float rate) {
        setPlaybackParameters(getPlaybackParameters().withSpeed(rate));
    }

    @Override // com.stremio.common.players.Player
    public float getPlaybackRate() {
        return getPlaybackParameters().speed;
    }

    @Override // com.stremio.common.players.Player
    public void setTrack(Track track) {
        TrackSelectionParameters.Builder builderBuildUpon = getTrackSelectionParameters().buildUpon();
        if (track == null) {
            builderBuildUpon.setTrackTypeDisabled(3, true);
        } else {
            int exoPlayerType = PlayerExtKt.toExoPlayerType(track.getType());
            builderBuildUpon.setTrackTypeDisabled(exoPlayerType, false);
            TrackSelectionOverride trackSelectionOverrideFindTrackOverride = findTrackOverride(exoPlayerType, track);
            if (trackSelectionOverrideFindTrackOverride != null) {
                builderBuildUpon.setOverrideForType(trackSelectionOverrideFindTrackOverride);
            }
        }
        setTrackSelectionParameters(builderBuildUpon.build());
    }

    @Override // com.stremio.common.players.Player
    public void setTextTrackDelay(long delay) {
        CustomTextRenderer textRenderer = getTextRenderer();
        if (textRenderer != null) {
            textRenderer.setRenderOffsetMs(delay);
        }
    }

    @Override // com.stremio.common.players.Player
    public void setTextTrackOffset(float offset) {
        SubtitleView subtitleView;
        PlayerView playerView = this.playerView;
        if (playerView == null || (subtitleView = playerView.getSubtitleView()) == null) {
            return;
        }
        subtitleView.setBottomPaddingFraction((offset / 100.0f) * 1.0f);
    }

    @Override // com.stremio.common.players.Player
    public void setTextTrackSize(float size) {
        SubtitleView subtitleView;
        PlayerView playerView = this.playerView;
        if (playerView == null || (subtitleView = playerView.getSubtitleView()) == null) {
            return;
        }
        subtitleView.setFractionalTextSize((size / 1900.0f) * 1.0f);
    }

    @Override // com.stremio.common.players.Player
    public void addExternalTextTracks(List<Track> tracks) {
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        MediaItem currentMediaItem = getCurrentMediaItem();
        if (currentMediaItem == null) {
            return;
        }
        MediaItem.LocalConfiguration localConfiguration = currentMediaItem.localConfiguration;
        ImmutableList<MediaItem.SubtitleConfiguration> immutableListEmptyList = localConfiguration != null ? localConfiguration.subtitleConfigurations : null;
        if (immutableListEmptyList == null) {
            immutableListEmptyList = CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = immutableListEmptyList.iterator();
        while (it.hasNext()) {
            String str = ((MediaItem.SubtitleConfiguration) it.next()).id;
            if (str != null) {
                arrayList.add(str);
            }
        }
        Set set = CollectionsKt.toSet(arrayList);
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : tracks) {
            if (!set.contains(((Track) obj).getId())) {
                arrayList2.add(obj);
            }
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : arrayList2) {
            if (hashSet.add(((Track) obj2).getId())) {
                arrayList3.add(obj2);
            }
        }
        ArrayList arrayList4 = arrayList3;
        if (arrayList4.isEmpty()) {
            return;
        }
        ArrayList<Track> arrayList5 = arrayList4;
        ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList5, 10));
        for (Track track : arrayList5) {
            Uri uri = track.getUri();
            Intrinsics.checkNotNull(uri);
            arrayList6.add(new MediaItem.SubtitleConfiguration.Builder(uri).setId(track.getId()).setMimeType(track.getMime()).setLanguage(track.getLanguage()).setLabel(track.getLabel()).setSelectionFlags(4).setRoleFlags(1048576).build());
        }
        MediaItem mediaItemBuild = currentMediaItem.buildUpon().setSubtitleConfigurations(CollectionsKt.plus(immutableListEmptyList, (Iterable) arrayList6)).build();
        Intrinsics.checkNotNullExpressionValue(mediaItemBuild, "build(...)");
        setMediaItem(mediaItemBuild, getCurrentPosition());
    }

    @Override // com.stremio.common.players.Player
    public void setVideoScale(VideoScale scale) {
        int i;
        Intrinsics.checkNotNullParameter(scale, "scale");
        this.videoScalingMode = scale;
        int i2 = WhenMappings.$EnumSwitchMapping$0[scale.ordinal()];
        if (i2 != 1) {
            i = i2 != 2 ? 0 : 3;
        } else {
            i = 4;
        }
        PlayerView playerView = this.playerView;
        if (playerView != null) {
            playerView.setResizeMode(i);
        }
        PlayerListener playerListener = this.playerListener;
        if (playerListener != null) {
            playerListener.onVideoScaleChanged(scale);
        }
    }

    private final CustomTextRenderer getTextRenderer() {
        CustomTextRenderer customTextRenderer;
        androidx.media3.exoplayer.ExoPlayer exoPlayerWrappedExoPlayer = wrappedExoPlayer();
        IntRange intRangeUntil = RangesKt.until(0, exoPlayerWrappedExoPlayer.getRendererCount());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10));
        Iterator<Integer> it = intRangeUntil.iterator();
        while (it.hasNext()) {
            arrayList.add(exoPlayerWrappedExoPlayer.getRenderer(((IntIterator) it).nextInt()));
        }
        Iterator it2 = arrayList.iterator();
        do {
            customTextRenderer = null;
            if (!it2.hasNext()) {
                break;
            }
            Renderer renderer = (Renderer) it2.next();
            if (renderer instanceof CustomTextRenderer) {
                customTextRenderer = (CustomTextRenderer) renderer;
            }
        } while (customTextRenderer == null);
        return customTextRenderer;
    }

    private final androidx.media3.exoplayer.ExoPlayer wrappedExoPlayer() {
        androidx.media3.common.Player wrappedPlayer = getWrappedPlayer();
        Intrinsics.checkNotNull(wrappedPlayer, "null cannot be cast to non-null type androidx.media3.exoplayer.ExoPlayer");
        return (androidx.media3.exoplayer.ExoPlayer) wrappedPlayer;
    }

    /* JADX INFO: renamed from: com.stremio.common.players.ExoPlayer$eventListener$1, reason: invalid class name */
    /* JADX INFO: compiled from: ExoPlayer.kt */
    @Metadata(d1 = {"\u0000]\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bH\u0016J \u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\bH\u0016J\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0018\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020!H\u0016¨\u0006\""}, d2 = {"com/stremio/common/players/ExoPlayer$eventListener$1", "Landroidx/media3/common/Player$Listener;", "onIsPlayingChanged", "", "isPlaying", "", "onPlaybackStateChanged", "playbackState", "", "onPlaybackParametersChanged", "playbackParameters", "Landroidx/media3/common/PlaybackParameters;", "onTimelineChanged", "timeline", "Landroidx/media3/common/Timeline;", "reason", "onPositionDiscontinuity", "oldPosition", "Landroidx/media3/common/Player$PositionInfo;", "newPosition", "onTracksChanged", "tracks", "Landroidx/media3/common/Tracks;", "onCues", "cueGroup", "Landroidx/media3/common/text/CueGroup;", "onEvents", "player", "Landroidx/media3/common/Player;", "events", "Landroidx/media3/common/Player$Events;", "onPlayerError", DownloadInfoExtKt.DOWNLOAD_STATUS_ERROR, "Landroidx/media3/common/PlaybackException;", "Stremio:common_release"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class AnonymousClass1 implements androidx.media3.common.Player.Listener {
        AnonymousClass1() {
        }

        @Override // androidx.media3.common.Player.Listener
        public void onIsPlayingChanged(boolean isPlaying) {
            PlayerListener playerListener = ExoPlayer.this.playerListener;
            if (playerListener != null) {
                playerListener.onPausedChanged(!isPlaying);
            }
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlaybackStateChanged(int playbackState) {
            PlayerListener playerListener;
            PlayerListener playerListener2 = ExoPlayer.this.playerListener;
            if (playerListener2 != null) {
                playerListener2.onBufferingChanged(playbackState == 2);
            }
            if (playbackState != 4 || (playerListener = ExoPlayer.this.playerListener) == null) {
                return;
            }
            playerListener.onEnded();
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            Intrinsics.checkNotNullParameter(playbackParameters, "playbackParameters");
            PlayerListener playerListener = ExoPlayer.this.playerListener;
            if (playerListener != null) {
                playerListener.onPlaybackRateChanged(playbackParameters.speed);
            }
        }

        @Override // androidx.media3.common.Player.Listener
        public void onTimelineChanged(Timeline timeline, int reason) {
            PlayerListener playerListener;
            Intrinsics.checkNotNullParameter(timeline, "timeline");
            if (timeline.getWindowCount() > 0) {
                Timeline.Window window = new Timeline.Window();
                timeline.getWindow(0, window);
                long durationMs = window.getDurationMs();
                if (durationMs == C.TIME_UNSET || (playerListener = ExoPlayer.this.playerListener) == null) {
                    return;
                }
                playerListener.onDurationChanged(durationMs);
            }
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPositionDiscontinuity(androidx.media3.common.Player.PositionInfo oldPosition, androidx.media3.common.Player.PositionInfo newPosition, int reason) {
            PlayerListener playerListener;
            Intrinsics.checkNotNullParameter(oldPosition, "oldPosition");
            Intrinsics.checkNotNullParameter(newPosition, "newPosition");
            if ((reason == 1 || reason == 2) && (playerListener = ExoPlayer.this.playerListener) != null) {
                playerListener.onSeek(ExoPlayer.this.getCurrentPosition(), ExoPlayer.this.getDuration());
            }
        }

        @Override // androidx.media3.common.Player.Listener
        public void onTracksChanged(Tracks tracks) {
            List tracks2;
            Intrinsics.checkNotNullParameter(tracks, "tracks");
            ImmutableList<Tracks.Group> groups = tracks.getGroups();
            Intrinsics.checkNotNullExpressionValue(groups, "getGroups(...)");
            ArrayList arrayList = new ArrayList();
            for (Tracks.Group group : groups) {
                if (group.isSupported()) {
                    arrayList.add(group);
                }
            }
            ExoPlayer exoPlayer = ExoPlayer.this;
            ArrayList arrayList2 = new ArrayList();
            int i = 0;
            for (Object obj : arrayList) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Tracks.Group group2 = (Tracks.Group) obj;
                int type = group2.getType();
                if (type == 1) {
                    Intrinsics.checkNotNull(group2);
                    tracks2 = exoPlayer.toTracks(group2, i, TrackType.AUDIO);
                } else if (type == 3) {
                    Intrinsics.checkNotNull(group2);
                    tracks2 = exoPlayer.toTracks(group2, i, TrackType.TEXT);
                } else {
                    tracks2 = CollectionsKt.emptyList();
                }
                CollectionsKt.addAll(arrayList2, tracks2);
                i = i2;
            }
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            for (Object obj2 : arrayList2) {
                if (((Track) obj2).getType() == TrackType.TEXT) {
                    arrayList3.add(obj2);
                } else {
                    arrayList4.add(obj2);
                }
            }
            Pair pair = new Pair(arrayList3, arrayList4);
            List<Track> list = (List) pair.component1();
            List<Track> list2 = (List) pair.component2();
            PlayerListener playerListener = ExoPlayer.this.playerListener;
            if (playerListener != null) {
                playerListener.onTracksChanged(list, list2);
            }
            ImmutableList<Tracks.Group> groups2 = tracks.getGroups();
            Intrinsics.checkNotNullExpressionValue(groups2, "getGroups(...)");
            List<Chapter> list3 = SequencesKt.toList(SequencesKt.sortedWith(SequencesKt.distinctBy(SequencesKt.flatMapIterable(CollectionsKt.asSequence(groups2), new ExoPlayer$eventListener$1$onTracksChanged$chapters$1(ExoPlayer.this)), new Function1() { // from class: com.stremio.common.players.ExoPlayer$eventListener$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    return Integer.valueOf(ExoPlayer.AnonymousClass1.onTracksChanged$lambda$3((Chapter) obj3));
                }
            }), new Comparator() { // from class: com.stremio.common.players.ExoPlayer$eventListener$1$onTracksChanged$$inlined$sortedBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Long.valueOf(((Chapter) t).getStartTime()), Long.valueOf(((Chapter) t2).getStartTime()));
                }
            }));
            PlayerListener playerListener2 = ExoPlayer.this.playerListener;
            if (playerListener2 != null) {
                playerListener2.onChaptersChanged(list3);
            }
        }

        static final int onTracksChanged$lambda$3(Chapter it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return it.getId();
        }

        @Override // androidx.media3.common.Player.Listener
        public void onCues(CueGroup cueGroup) {
            SubtitleView subtitleView;
            Intrinsics.checkNotNullParameter(cueGroup, "cueGroup");
            ImmutableList<Cue> cues = cueGroup.cues;
            Intrinsics.checkNotNullExpressionValue(cues, "cues");
            ImmutableList<Cue> immutableList = cues;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(immutableList, 10));
            for (Cue cue : immutableList) {
                Cue.Builder builderBuildUpon = cue.buildUpon();
                Intrinsics.checkNotNullExpressionValue(builderBuildUpon, "buildUpon(...)");
                CharSequence charSequence = cue.text;
                if (charSequence != null) {
                    builderBuildUpon.setText(ExoPlayer.NEW_LINE_REGEX.replace(charSequence, "\u200e$1"));
                }
                arrayList.add(builderBuildUpon.build());
            }
            ArrayList arrayList2 = arrayList;
            PlayerView playerView = ExoPlayer.this.playerView;
            if (playerView == null || (subtitleView = playerView.getSubtitleView()) == null) {
                return;
            }
            subtitleView.setCues(arrayList2);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onEvents(androidx.media3.common.Player player, androidx.media3.common.Player.Events events) {
            PlayerView playerView;
            SubtitleView subtitleView;
            Intrinsics.checkNotNullParameter(player, "player");
            Intrinsics.checkNotNullParameter(events, "events");
            if (events.contains(27)) {
                ImmutableList<Cue> cues = player.getCurrentCues().cues;
                Intrinsics.checkNotNullExpressionValue(cues, "cues");
                Cue cue = (Cue) CollectionsKt.singleOrNull((List) cues);
                if (cue == null || cue.text == null || cue.lineType != 1 || cue.line != -1.0f || (playerView = ExoPlayer.this.playerView) == null || (subtitleView = playerView.getSubtitleView()) == null) {
                    return;
                }
                subtitleView.setCues(CollectionsKt.listOf(cue.buildUpon().setLine(-3.4028235E38f, Integer.MIN_VALUE).build()));
            }
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlayerError(PlaybackException error) {
            Intrinsics.checkNotNullParameter(error, "error");
            PlayerListener playerListener = ExoPlayer.this.playerListener;
            if (playerListener != null) {
                playerListener.onError(error);
            }
        }
    }

    private final AnonymousClass1 eventListener() {
        return new AnonymousClass1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Track> toTracks(Tracks.Group group, int groupIndex, TrackType trackType) {
        IntRange intRangeUntil = RangesKt.until(0, group.length);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10));
        Iterator<Integer> it = intRangeUntil.iterator();
        while (it.hasNext()) {
            int iNextInt = ((IntIterator) it).nextInt();
            Format trackFormat = group.getTrackFormat(iNextInt);
            Intrinsics.checkNotNullExpressionValue(trackFormat, "getTrackFormat(...)");
            String trackId = toTrackId(trackFormat, groupIndex, iNextInt);
            String str = trackFormat.label;
            String str2 = trackFormat.language;
            if (str2 == null) {
                str2 = C.LANGUAGE_UNDETERMINED;
            }
            arrayList.add(new Track(trackType, trackId, null, str, str2, (trackFormat.roleFlags & 1048576) == 0, (trackFormat.roleFlags & 8) != 0 || (str != null && StringsKt.contains((CharSequence) str, (CharSequence) "commentary", true)), (trackFormat.roleFlags & 2) != 0 || (str != null && StringsKt.contains((CharSequence) str, (CharSequence) "forced", true)), trackFormat.codecs, trackFormat.sampleMimeType, null, group.isTrackSelected(iNextInt), AnalyticsListener.EVENT_PLAYER_RELEASED, null));
        }
        return arrayList;
    }

    private final String toTrackId(Format format, int i, int i2) {
        String str = format.id;
        if (str != null) {
            return (format.roleFlags & 1048576) != 0 ? StringsKt.substringAfter$default(str, ':', (String) null, 2, (Object) null) : str;
        }
        return i + "-" + i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Chapter> toChapters(Tracks.Group group) {
        IntRange intRangeUntil = RangesKt.until(0, group.length);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10));
        Iterator<Integer> it = intRangeUntil.iterator();
        if (it.hasNext()) {
            Format trackFormat = group.getTrackFormat(((IntIterator) it).nextInt());
            Intrinsics.checkNotNullExpressionValue(trackFormat, "getTrackFormat(...)");
            if (trackFormat.metadata != null) {
                androidx.media3.common.Metadata metadata = trackFormat.metadata;
                Intrinsics.checkNotNull(metadata);
                IntRange intRangeUntil2 = RangesKt.until(0, metadata.length());
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil2, 10));
                Iterator<Integer> it2 = intRangeUntil2.iterator();
                while (it2.hasNext()) {
                    int iNextInt = ((IntIterator) it2).nextInt();
                    androidx.media3.common.Metadata metadata2 = trackFormat.metadata;
                    Intrinsics.checkNotNull(metadata2);
                    arrayList2.add(metadata2.get(iNextInt));
                }
                ArrayList arrayList3 = new ArrayList();
                for (Object obj : arrayList2) {
                    if (obj instanceof androidx.media3.extractor.metadata.Chapter) {
                        arrayList3.add(obj);
                    }
                }
                ArrayList arrayList4 = arrayList3;
                ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList4, 10));
                int i = 0;
                for (Object obj2 : arrayList4) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    androidx.media3.extractor.metadata.Chapter chapter = (androidx.media3.extractor.metadata.Chapter) obj2;
                    Label title = chapter.getTitle();
                    arrayList5.add(new Chapter(i, title != null ? title.value : null, chapter.getStartTimeMs(), chapter.getEndTimeMs()));
                    i = i2;
                }
                return arrayList5;
            }
            return CollectionsKt.emptyList();
        }
        return arrayList;
    }

    private final TrackSelectionOverride findTrackOverride(final int type, Track track) {
        Object next;
        ImmutableList<Tracks.Group> groups = getCurrentTracks().getGroups();
        Intrinsics.checkNotNullExpressionValue(groups, "getGroups(...)");
        Iterator it = SequencesKt.flatMap(SequencesKt.filter(SequencesKt.withIndex(CollectionsKt.asSequence(groups)), new Function1() { // from class: com.stremio.common.players.ExoPlayer$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ExoPlayer.findTrackOverride$lambda$0(type, (IndexedValue) obj));
            }
        }), new Function1() { // from class: com.stremio.common.players.ExoPlayer$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ExoPlayer.findTrackOverride$lambda$1(this.f$0, (IndexedValue) obj);
            }
        }).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual((String) ((Triple) next).component3(), track.getId()));
        Triple triple = (Triple) next;
        if (triple == null) {
            return null;
        }
        return new TrackSelectionOverride(((Tracks.Group) triple.component1()).getMediaTrackGroup(), (List<Integer>) CollectionsKt.listOf(Integer.valueOf(((Number) triple.component2()).intValue())));
    }

    static final boolean findTrackOverride$lambda$0(int i, IndexedValue it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return ((Tracks.Group) it.getValue()).getType() == i;
    }

    static final Sequence findTrackOverride$lambda$1(final ExoPlayer exoPlayer, IndexedValue indexedValue) {
        Intrinsics.checkNotNullParameter(indexedValue, "<destruct>");
        final int iComponent1 = indexedValue.getIndex();
        final Tracks.Group group = (Tracks.Group) indexedValue.component2();
        return SequencesKt.map(CollectionsKt.asSequence(RangesKt.until(0, group.length)), new Function1() { // from class: com.stremio.common.players.ExoPlayer$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ExoPlayer.findTrackOverride$lambda$1$0(group, exoPlayer, iComponent1, ((Integer) obj).intValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Triple findTrackOverride$lambda$1$0(Tracks.Group group, ExoPlayer exoPlayer, int i, int i2) {
        Format trackFormat = group.getTrackFormat(i2);
        Intrinsics.checkNotNullExpressionValue(trackFormat, "getTrackFormat(...)");
        return new Triple(group, Integer.valueOf(i2), exoPlayer.toTrackId(trackFormat, i, i2));
    }
}