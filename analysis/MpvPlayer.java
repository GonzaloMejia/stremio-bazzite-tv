package com.stremio.common.players;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.stremio.common.extensions.DownloadInfoExtKt;
import com.stremio.core.types.profile.Profile;
import com.stremio.tv.Constants;
import dev.jdtech.mpv.MPVLib;
import io.ktor.http.ContentDisposition;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.json.JSONObject;

/* JADX INFO: compiled from: MpvPlayer.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u008f\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u000f\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u001bH\u0002J\u0012\u0010 \u001a\u00020\u00192\b\u0010!\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010\"\u001a\u00020\u0019H\u0016J\u0010\u0010#\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%H\u0016J\u0010\u0010&\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%H\u0016J\u0010\u0010'\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%H\u0016J\u0018\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0016J\b\u0010-\u001a\u00020\u0019H\u0016J\b\u0010.\u001a\u00020\u0019H\u0016J\b\u0010/\u001a\u00020\u0019H\u0016J\b\u00100\u001a\u000201H\u0016J\b\u00102\u001a\u00020,H\u0016J\u0010\u00103\u001a\u00020\u00192\u0006\u00104\u001a\u00020,H\u0016J\b\u00105\u001a\u00020,H\u0016J\b\u00106\u001a\u00020,H\u0016J\u0010\u00107\u001a\u00020\u00192\u0006\u00108\u001a\u000209H\u0016J\b\u0010:\u001a\u000209H\u0016J\u0012\u0010;\u001a\u00020\u00192\b\u0010<\u001a\u0004\u0018\u00010\u0015H\u0016J\u0010\u0010=\u001a\u00020\u00192\u0006\u0010>\u001a\u00020,H\u0016J\u0010\u0010?\u001a\u00020\u00192\u0006\u0010>\u001a\u00020,H\u0016J\u0010\u0010@\u001a\u00020\u00192\u0006\u0010A\u001a\u000209H\u0016J\u0010\u0010B\u001a\u00020\u00192\u0006\u0010C\u001a\u000209H\u0016J\u0016\u0010D\u001a\u00020\u00192\f\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0016J\u0010\u0010F\u001a\u00020\u00192\u0006\u0010G\u001a\u00020HH\u0016J\r\u0010\u000e\u001a\u00020\u000fH\u0002¢\u0006\u0002\u0010IR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\u00020\u001dX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fÊ\u0001\f\bK\u0012\b\bL\u0012\u0004\b\u0003\u0010\u0000¨\u0006J"}, d2 = {"Lcom/stremio/common/players/MpvPlayer;", "Lcom/stremio/common/players/Player;", "context", "Landroid/content/Context;", "settings", "Lcom/stremio/core/types/profile/Profile$Settings;", "videoOutput", "", "<init>", "(Landroid/content/Context;Lcom/stremio/core/types/profile/Profile$Settings;Ljava/lang/String;)V", "mpv", "Ldev/jdtech/mpv/MPVLib;", "playerListener", "Lcom/stremio/common/players/PlayerListener;", "eventObserver", "com/stremio/common/players/MpvPlayer$eventObserver$1", "Lcom/stremio/common/players/MpvPlayer$eventObserver$1;", "state", "Lcom/stremio/common/players/MPVState;", "externalTextTracks", "", "Lcom/stremio/common/players/Track;", "mainHandler", "Landroid/os/Handler;", "onMain", "", "block", "Lkotlin/Function0;", "capabilities", "Lcom/stremio/common/players/PlayerCapabilities;", "getCapabilities", "()Lcom/stremio/common/players/PlayerCapabilities;", "prepare", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "release", "attachView", "view", "Landroid/view/View;", "detachView", "updateView", "load", "uri", "Landroid/net/Uri;", "startTime", "", "play", Property.PAUSED, "stop", "isPlaying", "", "getBufferedTime", "setTime", "time", "getTime", "getDuration", "setPlaybackRate", "rate", "", "getPlaybackRate", "setTrack", "track", "setAudioTrackDelay", "delay", "setTextTrackDelay", "setTextTrackSize", ContentDisposition.Parameters.Size, "setTextTrackOffset", TypedValues.CycleType.S_WAVE_OFFSET, "addExternalTextTracks", "tracks", "setVideoScale", "scale", "Lcom/stremio/common/players/VideoScale;", "()Lcom/stremio/common/players/MpvPlayer$eventObserver$1;", "Stremio:common_release", "Landroidx/compose/runtime/internal/StabilityInferred;", "parameters"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class MpvPlayer implements Player {
    public static final int $stable = 8;
    private final PlayerCapabilities capabilities;
    private final Context context;
    private final AnonymousClass1 eventObserver;
    private List<Track> externalTextTracks;
    private final Handler mainHandler;
    private MPVLib mpv;
    private PlayerListener playerListener;
    private MPVState state;
    private final String videoOutput;

    /* JADX INFO: compiled from: MpvPlayer.kt */
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

    public MpvPlayer(Context context, Profile.Settings settings, String videoOutput) throws IOException {
        String str;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(videoOutput, "videoOutput");
        this.context = context;
        this.videoOutput = videoOutput;
        this.eventObserver = eventObserver();
        this.state = new MPVState(false, 0L, 0L, 0L, Constants.MIN_SUBTITLES_OFFSET, 31, null);
        this.externalTextTracks = CollectionsKt.emptyList();
        this.mainHandler = new Handler(Looper.getMainLooper());
        File externalFilesDir = context.getExternalFilesDir(null);
        File file = new File(externalFilesDir == null ? context.getFilesDir() : externalFilesDir, "mpv");
        if (!file.exists()) {
            file.mkdirs();
        }
        String str2 = new String[]{"droid-sans-fallback.ttf"}[0];
        File file2 = new File(file, str2);
        if (!file2.exists()) {
            InputStream inputStreamOpen = context.getAssets().open(str2, 2);
            Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "open(...)");
            ByteStreamsKt.copyTo$default(inputStreamOpen, new FileOutputStream(file2), 0, 2, null);
        }
        this.mpv = MPVLib.INSTANCE.create(context);
        boolean hardwareDecoding = settings.getHardwareDecoding();
        if (hardwareDecoding) {
            str = "mediacodec,mediacodec-copy,no";
        } else {
            if (hardwareDecoding) {
                throw new NoWhenBranchMatchedException();
            }
            str = "no";
        }
        MPVLib mPVLib = this.mpv;
        if (mPVLib != null) {
            mPVLib.setOptionString("tls-verify", "no");
        }
        MPVLib mPVLib2 = this.mpv;
        if (mPVLib2 != null) {
            mPVLib2.setOptionString("tls-ca-file", "");
        }
        int i = Build.VERSION.SDK_INT >= 27 ? 64 : 32;
        MPVLib mPVLib3 = this.mpv;
        if (mPVLib3 != null) {
            mPVLib3.setOptionString("demuxer-max-bytes", String.valueOf(i * 1048576));
        }
        MPVLib mPVLib4 = this.mpv;
        if (mPVLib4 != null) {
            mPVLib4.setOptionString("demuxer-max-back-bytes", String.valueOf(i * 1048576));
        }
        MPVLib mPVLib5 = this.mpv;
        if (mPVLib5 != null) {
            String path = file.getPath();
            Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
            mPVLib5.setOptionString("sub-fonts-dir", path);
        }
        MPVLib mPVLib6 = this.mpv;
        if (mPVLib6 != null) {
            mPVLib6.setOptionString("sub-font", "Droid Sans Fallback");
        }
        MPVLib mPVLib7 = this.mpv;
        if (mPVLib7 != null) {
            mPVLib7.setOptionString("sub-font-size", "52");
        }
        MPVLib mPVLib8 = this.mpv;
        if (mPVLib8 != null) {
            String path2 = context.getCacheDir().getPath();
            Intrinsics.checkNotNullExpressionValue(path2, "getPath(...)");
            mPVLib8.setOptionString("gpu-shader-cache-dir", path2);
        }
        MPVLib mPVLib9 = this.mpv;
        if (mPVLib9 != null) {
            String path3 = context.getCacheDir().getPath();
            Intrinsics.checkNotNullExpressionValue(path3, "getPath(...)");
            mPVLib9.setOptionString("icc-cache-dir", path3);
        }
        MPVLib mPVLib10 = this.mpv;
        if (mPVLib10 != null) {
            mPVLib10.setOptionString("vo", videoOutput);
        }
        MPVLib mPVLib11 = this.mpv;
        if (mPVLib11 != null) {
            mPVLib11.setOptionString("gpu-context", "android");
        }
        MPVLib mPVLib12 = this.mpv;
        if (mPVLib12 != null) {
            mPVLib12.setOptionString("opengl-es", "yes");
        }
        MPVLib mPVLib13 = this.mpv;
        if (mPVLib13 != null) {
            mPVLib13.setOptionString("hwdec", str);
        }
        MPVLib mPVLib14 = this.mpv;
        if (mPVLib14 != null) {
            mPVLib14.setOptionString("hwdec-codecs", "h264,hevc,mpeg4,mpeg2video,vp8,vp9,av1");
        }
        MPVLib mPVLib15 = this.mpv;
        if (mPVLib15 != null) {
            mPVLib15.setOptionString("deband", "no");
        }
        MPVLib mPVLib16 = this.mpv;
        if (mPVLib16 != null) {
            mPVLib16.setOptionString("video-sync", "audio");
        }
        MPVLib mPVLib17 = this.mpv;
        if (mPVLib17 != null) {
            mPVLib17.setOptionString(Scopes.PROFILE, "fast");
        }
        MPVLib mPVLib18 = this.mpv;
        if (mPVLib18 != null) {
            mPVLib18.setOptionString("ao", "audiotrack,opensles");
        }
        MPVLib mPVLib19 = this.mpv;
        if (mPVLib19 != null) {
            mPVLib19.setOptionString("sid", "no");
        }
        MPVLib mPVLib20 = this.mpv;
        if (mPVLib20 != null) {
            mPVLib20.setOptionString("ytdl", "no");
        }
        MPVLib mPVLib21 = this.mpv;
        if (mPVLib21 != null) {
            mPVLib21.setOptionString("hr-seek", "no");
        }
        MPVLib mPVLib22 = this.mpv;
        if (mPVLib22 != null) {
            mPVLib22.setOptionString("hr-seek-framedrop", "yes");
        }
        MPVLib mPVLib23 = this.mpv;
        if (mPVLib23 != null) {
            mPVLib23.setOptionString("force-window", "no");
        }
        MPVLib mPVLib24 = this.mpv;
        if (mPVLib24 != null) {
            mPVLib24.setOptionString("idle", "once");
        }
        MPVLib mPVLib25 = this.mpv;
        if (mPVLib25 != null) {
            mPVLib25.init();
        }
        MPVLib mPVLib26 = this.mpv;
        if (mPVLib26 != null) {
            mPVLib26.setPropertyDouble(Property.TEXT_TRACK_SIZE, ((double) settings.getSubtitlesSize()) / 100.0d);
        }
        MPVLib mPVLib27 = this.mpv;
        if (mPVLib27 != null) {
            mPVLib27.setPropertyDouble(Property.TEXT_TRACK_OFFSET, 105.0d - ((double) settings.getSubtitlesOffset()));
        }
        MPVLib mPVLib28 = this.mpv;
        if (mPVLib28 != null) {
            mPVLib28.setPropertyString(Property.TEXT_TRACK_FOREGROUND_COLOR, settings.getSubtitlesTextColor());
        }
        MPVLib mPVLib29 = this.mpv;
        if (mPVLib29 != null) {
            mPVLib29.setPropertyString(Property.TEXT_TRACK_BACKGROUND_STYLE, "background-box");
        }
        MPVLib mPVLib30 = this.mpv;
        if (mPVLib30 != null) {
            mPVLib30.setPropertyString(Property.TEXT_TRACK_BACKGROUND_COLOR, settings.getSubtitlesBackgroundColor());
        }
        MPVLib mPVLib31 = this.mpv;
        if (mPVLib31 != null) {
            mPVLib31.setPropertyString(Property.TEXT_TRACK_OUTLINE_COLOR, settings.getSubtitlesOutlineColor());
        }
        MPVLib mPVLib32 = this.mpv;
        if (mPVLib32 != null) {
            mPVLib32.observeProperty(Property.PAUSED, 3);
        }
        MPVLib mPVLib33 = this.mpv;
        if (mPVLib33 != null) {
            mPVLib33.observeProperty(Property.SEEKING, 3);
        }
        MPVLib mPVLib34 = this.mpv;
        if (mPVLib34 != null) {
            mPVLib34.observeProperty(Property.BUFFERING, 4);
        }
        MPVLib mPVLib35 = this.mpv;
        if (mPVLib35 != null) {
            mPVLib35.observeProperty(Property.POSITION, 5);
        }
        MPVLib mPVLib36 = this.mpv;
        if (mPVLib36 != null) {
            mPVLib36.observeProperty("duration", 5);
        }
        MPVLib mPVLib37 = this.mpv;
        if (mPVLib37 != null) {
            mPVLib37.observeProperty(Property.SPEED, 5);
        }
        MPVLib mPVLib38 = this.mpv;
        if (mPVLib38 != null) {
            mPVLib38.observeProperty(Property.TRACKS, 1);
        }
        MPVLib mPVLib39 = this.mpv;
        if (mPVLib39 != null) {
            mPVLib39.observeProperty(Property.CHAPTERS, 1);
        }
        IntProgression intProgressionStep = RangesKt.step(new IntRange(25, 250), 25);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intProgressionStep, 10));
        Iterator<Integer> it = intProgressionStep.iterator();
        while (it.hasNext()) {
            arrayList.add(Float.valueOf(((IntIterator) it).nextInt() / 100.0f));
        }
        this.capabilities = new PlayerCapabilities(true, true, true, true, true, true, arrayList, true, CollectionsKt.listOf((Object[]) new VideoScale[]{VideoScale.FIT, VideoScale.CROP, VideoScale.STRETCH}));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onMain(final Function0<Unit> block) {
        if (Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
            block.invoke();
        } else {
            this.mainHandler.post(new Runnable() { // from class: com.stremio.common.players.MpvPlayer$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    block.invoke();
                }
            });
        }
    }

    @Override // com.stremio.common.players.Player
    public PlayerCapabilities getCapabilities() {
        return this.capabilities;
    }

    @Override // com.stremio.common.players.Player
    public void prepare(PlayerListener listener) {
        this.playerListener = listener;
        MPVLib mPVLib = this.mpv;
        if (mPVLib != null) {
            mPVLib.addObserver(this.eventObserver);
        }
    }

    @Override // com.stremio.common.players.Player
    public void release() {
        stop();
        this.playerListener = null;
        MPVLib mPVLib = this.mpv;
        if (mPVLib != null) {
            mPVLib.removeObserver(this.eventObserver);
        }
        MPVLib mPVLib2 = this.mpv;
        if (mPVLib2 != null) {
            mPVLib2.destroy();
        }
        this.mpv = null;
    }

    @Override // com.stremio.common.players.Player
    public void attachView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        MPVLib mPVLib = this.mpv;
        if (mPVLib != null) {
            Surface surface = ((SurfaceView) view).getHolder().getSurface();
            Intrinsics.checkNotNullExpressionValue(surface, "getSurface(...)");
            mPVLib.attachSurface(surface);
        }
        MPVLib mPVLib2 = this.mpv;
        if (mPVLib2 != null) {
            mPVLib2.setOptionString("force-window", "yes");
        }
        MPVLib mPVLib3 = this.mpv;
        if (mPVLib3 != null) {
            mPVLib3.setPropertyString("vo", this.videoOutput);
        }
    }

    @Override // com.stremio.common.players.Player
    public void detachView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        MPVLib mPVLib = this.mpv;
        if (mPVLib != null) {
            mPVLib.setPropertyString("vo", AbstractJsonLexerKt.NULL);
        }
        MPVLib mPVLib2 = this.mpv;
        if (mPVLib2 != null) {
            mPVLib2.setOptionString("force-window", "no");
        }
        MPVLib mPVLib3 = this.mpv;
        if (mPVLib3 != null) {
            mPVLib3.detachSurface();
        }
    }

    @Override // com.stremio.common.players.Player
    public void updateView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        MPVLib mPVLib = this.mpv;
        if (mPVLib != null) {
            mPVLib.setPropertyString("android-surface-size", view.getWidth() + "x" + view.getHeight());
        }
    }

    @Override // com.stremio.common.players.Player
    public void load(Uri uri, long startTime) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        double d = startTime / 1000.0d;
        MPVLib mPVLib = this.mpv;
        if (mPVLib != null) {
            mPVLib.command(new String[]{"loadfile", uri.toString(), "replace", "-1", "start=+" + d});
        }
    }

    @Override // com.stremio.common.players.Player
    public void play() {
        MPVLib mPVLib = this.mpv;
        if (mPVLib != null) {
            mPVLib.setPropertyBoolean(Property.PAUSED, false);
        }
    }

    @Override // com.stremio.common.players.Player
    public void pause() {
        MPVLib mPVLib = this.mpv;
        if (mPVLib != null) {
            mPVLib.setPropertyBoolean(Property.PAUSED, true);
        }
    }

    @Override // com.stremio.common.players.Player
    public void stop() {
        MPVLib mPVLib = this.mpv;
        if (mPVLib != null) {
            mPVLib.command(new String[]{"stop"});
        }
    }

    @Override // com.stremio.common.players.Player
    public boolean isPlaying() {
        return !this.state.getPaused();
    }

    @Override // com.stremio.common.players.Player
    public long getBufferedTime() {
        return this.state.getBufferedTime();
    }

    @Override // com.stremio.common.players.Player
    public void setTime(long time) {
        double d = time / 1000.0d;
        MPVLib mPVLib = this.mpv;
        if (mPVLib != null) {
            mPVLib.setPropertyDouble(Property.POSITION, d);
        }
        PlayerListener playerListener = this.playerListener;
        if (playerListener != null) {
            playerListener.onSeek(time, getDuration());
        }
    }

    @Override // com.stremio.common.players.Player
    public long getTime() {
        return this.state.getTime();
    }

    @Override // com.stremio.common.players.Player
    public long getDuration() {
        return this.state.getDuration();
    }

    @Override // com.stremio.common.players.Player
    public void setPlaybackRate(float rate) {
        MPVLib mPVLib = this.mpv;
        if (mPVLib != null) {
            mPVLib.setPropertyDouble(Property.SPEED, rate);
        }
    }

    @Override // com.stremio.common.players.Player
    public float getPlaybackRate() {
        return (float) this.state.getSpeed();
    }

    private static final void setTrack$setExternalTrack(MpvPlayer mpvPlayer, String str) {
        List<Track> list = mpvPlayer.externalTextTracks;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (Track track : list) {
            arrayList.add(Track.copy$default(track, null, null, null, null, null, false, false, false, null, null, null, Intrinsics.areEqual(track.getId(), str), 2047, null));
        }
        mpvPlayer.externalTextTracks = arrayList;
    }

    @Override // com.stremio.common.players.Player
    public void setTrack(Track track) {
        if (track == null) {
            MPVLib mPVLib = this.mpv;
            if (mPVLib != null) {
                mPVLib.setPropertyString("sid", "no");
            }
            setTrack$setExternalTrack(this, null);
        }
        if ((track != null ? track.getType() : null) == TrackType.TEXT) {
            Uri uri = track.getUri();
            MPVLib mPVLib2 = this.mpv;
            if (uri != null) {
                if (mPVLib2 != null) {
                    mPVLib2.command(new String[]{"sub-add", track.getUri().toString(), "select"});
                }
                setTrack$setExternalTrack(this, track.getId());
            } else {
                if (mPVLib2 != null) {
                    mPVLib2.setPropertyString("sid", track.getId());
                }
                setTrack$setExternalTrack(this, null);
            }
        }
        if ((track != null ? track.getType() : null) == TrackType.AUDIO) {
            MPVLib mPVLib3 = this.mpv;
            if (mPVLib3 != null) {
                mPVLib3.setPropertyString(Property.AUDIO_TRACK, track.getId());
            }
            setTrack$setExternalTrack(this, null);
        }
    }

    @Override // com.stremio.common.players.Player
    public void setAudioTrackDelay(long delay) {
        MPVLib mPVLib = this.mpv;
        if (mPVLib != null) {
            mPVLib.setPropertyDouble(Property.AUDIO_TRACK_DELAY, delay);
        }
    }

    @Override // com.stremio.common.players.Player
    public void setTextTrackDelay(long delay) {
        MPVLib mPVLib = this.mpv;
        if (mPVLib != null) {
            mPVLib.setPropertyDouble(Property.TEXT_TRACK_DELAY, delay);
        }
    }

    @Override // com.stremio.common.players.Player
    public void setTextTrackSize(float size) {
        double d = ((double) size) / 100.0d;
        MPVLib mPVLib = this.mpv;
        if (mPVLib != null) {
            mPVLib.setPropertyDouble(Property.TEXT_TRACK_SIZE, d);
        }
    }

    @Override // com.stremio.common.players.Player
    public void setTextTrackOffset(float offset) {
        double d = 105.0d - ((double) offset);
        MPVLib mPVLib = this.mpv;
        if (mPVLib != null) {
            mPVLib.setPropertyDouble(Property.TEXT_TRACK_OFFSET, d);
        }
    }

    @Override // com.stremio.common.players.Player
    public void addExternalTextTracks(List<Track> tracks) {
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        List listPlus = CollectionsKt.plus((Collection) this.externalTextTracks, (Iterable) tracks);
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (Object obj : listPlus) {
            if (hashSet.add(((Track) obj).getId())) {
                arrayList.add(obj);
            }
        }
        this.externalTextTracks = arrayList;
    }

    @Override // com.stremio.common.players.Player
    public void setVideoScale(VideoScale scale) {
        Intrinsics.checkNotNullParameter(scale, "scale");
        int i = WhenMappings.$EnumSwitchMapping$0[scale.ordinal()];
        if (i == 1) {
            MPVLib mPVLib = this.mpv;
            if (mPVLib != null) {
                mPVLib.setPropertyString("keepaspect", "yes");
            }
            MPVLib mPVLib2 = this.mpv;
            if (mPVLib2 != null) {
                mPVLib2.setPropertyDouble("panscan", 1.0d);
            }
        } else {
            MPVLib mPVLib3 = this.mpv;
            if (i == 2) {
                if (mPVLib3 != null) {
                    mPVLib3.setPropertyString("keepaspect", "no");
                }
                MPVLib mPVLib4 = this.mpv;
                if (mPVLib4 != null) {
                    mPVLib4.setPropertyDouble("panscan", Constants.MIN_SUBTITLES_OFFSET);
                }
            } else {
                if (mPVLib3 != null) {
                    mPVLib3.setPropertyString("keepaspect", "yes");
                }
                MPVLib mPVLib5 = this.mpv;
                if (mPVLib5 != null) {
                    mPVLib5.setPropertyDouble("panscan", Constants.MIN_SUBTITLES_OFFSET);
                }
            }
        }
        PlayerListener playerListener = this.playerListener;
        if (playerListener != null) {
            playerListener.onVideoScaleChanged(scale);
        }
    }

    /* JADX INFO: renamed from: com.stremio.common.players.MpvPlayer$eventObserver$1, reason: invalid class name */
    /* JADX INFO: compiled from: MpvPlayer.kt */
    @Metadata(d1 = {"\u0000/\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\bH\u0016J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\tH\u0016J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\fH\u0016¨\u0006\u0010"}, d2 = {"com/stremio/common/players/MpvPlayer$eventObserver$1", "Ldev/jdtech/mpv/MPVLib$EventObserver;", "eventProperty", "", "property", "", "value", "", "", "", "endFile", "reason", "", DownloadInfoExtKt.DOWNLOAD_STATUS_ERROR, NotificationCompat.CATEGORY_EVENT, "eventId", "Stremio:common_release"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class AnonymousClass1 implements MPVLib.EventObserver {
        @Override // dev.jdtech.mpv.MPVLib.EventObserver
        public void event(int eventId) {
        }

        @Override // dev.jdtech.mpv.MPVLib.EventObserver
        public void eventProperty(String property) {
            Intrinsics.checkNotNullParameter(property, "property");
        }

        AnonymousClass1() {
        }

        @Override // dev.jdtech.mpv.MPVLib.EventObserver
        public void eventProperty(final String property, final boolean value) {
            Intrinsics.checkNotNullParameter(property, "property");
            final MpvPlayer mpvPlayer = MpvPlayer.this;
            mpvPlayer.onMain(new Function0() { // from class: com.stremio.common.players.MpvPlayer$eventObserver$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MpvPlayer.AnonymousClass1.eventProperty$lambda$0(property, mpvPlayer, value);
                }
            });
        }

        static final Unit eventProperty$lambda$0(String str, MpvPlayer mpvPlayer, boolean z) {
            PlayerListener playerListener;
            if (Intrinsics.areEqual(str, Property.PAUSED)) {
                mpvPlayer.state.setPaused(z);
                PlayerListener playerListener2 = mpvPlayer.playerListener;
                if (playerListener2 != null) {
                    playerListener2.onPausedChanged(mpvPlayer.state.getPaused());
                }
            } else if (Intrinsics.areEqual(str, Property.SEEKING) && (playerListener = mpvPlayer.playerListener) != null) {
                playerListener.onBufferingChanged(z);
            }
            return Unit.INSTANCE;
        }

        @Override // dev.jdtech.mpv.MPVLib.EventObserver
        public void eventProperty(final String property, final double value) {
            Intrinsics.checkNotNullParameter(property, "property");
            final MpvPlayer mpvPlayer = MpvPlayer.this;
            mpvPlayer.onMain(new Function0() { // from class: com.stremio.common.players.MpvPlayer$eventObserver$1$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MpvPlayer.AnonymousClass1.eventProperty$lambda$1(property, mpvPlayer, value);
                }
            });
        }

        static final Unit eventProperty$lambda$1(String str, MpvPlayer mpvPlayer, double d) {
            int iHashCode = str.hashCode();
            if (iHashCode != -2078520492) {
                if (iHashCode != -1992012396) {
                    if (iHashCode == 109641799 && str.equals(Property.SPEED)) {
                        mpvPlayer.state.setSpeed(d);
                        PlayerListener playerListener = mpvPlayer.playerListener;
                        if (playerListener != null) {
                            playerListener.onPlaybackRateChanged((float) d);
                        }
                    }
                } else if (str.equals("duration")) {
                    MPVState mPVState = mpvPlayer.state;
                    MPVLib mPVLib = mpvPlayer.mpv;
                    mPVState.setPaused(mPVLib != null ? Intrinsics.areEqual((Object) mPVLib.getPropertyBoolean(Property.PAUSED), (Object) true) : false);
                    PlayerListener playerListener2 = mpvPlayer.playerListener;
                    if (playerListener2 != null) {
                        playerListener2.onPausedChanged(mpvPlayer.state.getPaused());
                    }
                    mpvPlayer.state.setDuration((long) (d * 1000.0d));
                    PlayerListener playerListener3 = mpvPlayer.playerListener;
                    if (playerListener3 != null) {
                        playerListener3.onDurationChanged(mpvPlayer.state.getDuration());
                    }
                }
            } else if (str.equals(Property.POSITION)) {
                mpvPlayer.state.setTime((long) (d * 1000.0d));
                PlayerListener playerListener4 = mpvPlayer.playerListener;
                if (playerListener4 != null) {
                    playerListener4.onTimeChanged(mpvPlayer.state.getTime(), mpvPlayer.getBufferedTime(), mpvPlayer.getDuration());
                }
            }
            return Unit.INSTANCE;
        }

        @Override // dev.jdtech.mpv.MPVLib.EventObserver
        public void eventProperty(final String property, final long value) {
            Intrinsics.checkNotNullParameter(property, "property");
            final MpvPlayer mpvPlayer = MpvPlayer.this;
            mpvPlayer.onMain(new Function0() { // from class: com.stremio.common.players.MpvPlayer$eventObserver$1$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MpvPlayer.AnonymousClass1.eventProperty$lambda$2(property, mpvPlayer, value);
                }
            });
        }

        static final Unit eventProperty$lambda$2(String str, MpvPlayer mpvPlayer, long j) {
            PlayerListener playerListener;
            if (Intrinsics.areEqual(str, Property.BUFFERING) && (playerListener = mpvPlayer.playerListener) != null) {
                playerListener.onBufferingChanged(j < 100);
            }
            return Unit.INSTANCE;
        }

        @Override // dev.jdtech.mpv.MPVLib.EventObserver
        public void eventProperty(final String property, final String value) {
            Intrinsics.checkNotNullParameter(property, "property");
            Intrinsics.checkNotNullParameter(value, "value");
            final MpvPlayer mpvPlayer = MpvPlayer.this;
            mpvPlayer.onMain(new Function0() { // from class: com.stremio.common.players.MpvPlayer$eventObserver$1$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MpvPlayer.AnonymousClass1.eventProperty$lambda$3(property, value, mpvPlayer);
                }
            });
        }

        static final Unit eventProperty$lambda$3(String str, String str2, MpvPlayer mpvPlayer) {
            if (Intrinsics.areEqual(str, Property.TRACKS)) {
                List jSONList = MpvPlayerKt.parseJSONList(str2);
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(jSONList, 10));
                Iterator it = jSONList.iterator();
                while (it.hasNext()) {
                    arrayList.add(MpvPlayerKt.toTrack((JSONObject) it.next()));
                }
                ArrayList arrayList2 = arrayList;
                ArrayList arrayList3 = new ArrayList();
                for (Object obj : arrayList2) {
                    Track track = (Track) obj;
                    if (track.getType() == TrackType.TEXT && track.getEmbedded()) {
                        arrayList3.add(obj);
                    }
                }
                ArrayList arrayList4 = arrayList3;
                ArrayList arrayList5 = new ArrayList();
                for (Object obj2 : arrayList2) {
                    if (((Track) obj2).getType() == TrackType.AUDIO) {
                        arrayList5.add(obj2);
                    }
                }
                ArrayList arrayList6 = arrayList5;
                List<Track> listPlus = CollectionsKt.plus((Collection) arrayList4, (Iterable) mpvPlayer.externalTextTracks);
                PlayerListener playerListener = mpvPlayer.playerListener;
                if (playerListener != null) {
                    playerListener.onTracksChanged(listPlus, arrayList6);
                }
            } else if (Intrinsics.areEqual(str, Property.CHAPTERS)) {
                List jSONList2 = MpvPlayerKt.parseJSONList(str2);
                ArrayList arrayList7 = new ArrayList(CollectionsKt.collectionSizeOrDefault(jSONList2, 10));
                int i = 0;
                for (Object obj3 : jSONList2) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    arrayList7.add(MpvPlayerKt.toChapter(i, (JSONObject) obj3));
                    i = i2;
                }
                ArrayList arrayList8 = arrayList7;
                PlayerListener playerListener2 = mpvPlayer.playerListener;
                if (playerListener2 != null) {
                    playerListener2.onChaptersChanged(arrayList8);
                }
            }
            return Unit.INSTANCE;
        }

        @Override // dev.jdtech.mpv.MPVLib.EventObserver
        public void endFile(final int reason, final int error) {
            final MpvPlayer mpvPlayer = MpvPlayer.this;
            mpvPlayer.onMain(new Function0() { // from class: com.stremio.common.players.MpvPlayer$eventObserver$1$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MpvPlayer.AnonymousClass1.endFile$lambda$4(reason, mpvPlayer, error);
                }
            });
        }

        static final Unit endFile$lambda$4(int i, MpvPlayer mpvPlayer, int i2) {
            if (i == 4) {
                PlayerListener playerListener = mpvPlayer.playerListener;
                if (playerListener != null) {
                    playerListener.onBufferingChanged(false);
                }
                PlayerListener playerListener2 = mpvPlayer.playerListener;
                if (playerListener2 != null) {
                    playerListener2.onError(new MPVException(i2));
                }
            } else {
                PlayerListener playerListener3 = mpvPlayer.playerListener;
                if (playerListener3 != null) {
                    playerListener3.onEnded();
                }
            }
            return Unit.INSTANCE;
        }
    }

    private final AnonymousClass1 eventObserver() {
        return new AnonymousClass1();
    }
}