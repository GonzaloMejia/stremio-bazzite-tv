package com.stremio.core.models;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.FrameMetricsAggregator;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.stremio.core.types.addon.ResourcePath;
import com.stremio.core.types.addon.ResourceRequest;
import com.stremio.core.types.library.LibraryItem;
import com.stremio.core.types.resource.Stream;
import com.stremio.core.types.resource.Video;
import com.stremio.tv.views.settings.Tabs;
import io.ktor.http.ContentDisposition;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import pbandk.Export;
import pbandk.FieldDescriptor;
import pbandk.Message;
import pbandk.MessageDecoder;
import pbandk.MessageDescriptor;
import pbandk.UnknownField;
import pbandk.wkt.FieldOptions;

/* JADX INFO: compiled from: player.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\b\u0087\b\u0018\u0000 P2\u00020\u0001:\bOPQRSTUVB\u0097\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u0012\u0014\b\u0002\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u0018¢\u0006\u0002\u0010\u001bJ\u000b\u0010;\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0016HÆ\u0003J\u0015\u0010=\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u0018HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000f\u0010@\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\fHÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u000eHÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0014HÆ\u0003J\u009b\u0001\u0010F\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0014\b\u0002\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u0018HÆ\u0001J\u0013\u0010G\u001a\u00020H2\b\u0010I\u001a\u0004\u0018\u00010JHÖ\u0003J\t\u0010K\u001a\u00020\u0019HÖ\u0001J\u0013\u0010L\u001a\u00020\u00002\b\u0010I\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\t\u0010M\u001a\u00020NHÖ\u0001R\u001a\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00000\u001d8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u001b\u0010(\u001a\u00020\u00198VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b)\u0010*R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0016¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R \u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u0018X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b7\u00108R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:¨\u0006W"}, d2 = {"Lcom/stremio/core/models/Player;", "Lpbandk/Message;", "selected", "Lcom/stremio/core/models/Player$Selected;", "videoParams", "Lcom/stremio/core/models/Player$VideoParams;", "metaItem", "Lcom/stremio/core/models/LoadableMetaItem;", Tabs.SUBTITLES, "", "Lcom/stremio/core/models/LoadableSubtitles;", "nextVideo", "Lcom/stremio/core/types/resource/Video;", "seriesInfo", "Lcom/stremio/core/types/resource/Video$SeriesInfo;", "libraryItem", "Lcom/stremio/core/types/library/LibraryItem;", "streamState", "Lcom/stremio/core/models/Player$StreamState;", "introOutro", "Lcom/stremio/core/models/Player$IntroOutro;", "stream", "Lcom/stremio/core/models/LoadableConvertedStream;", "unknownFields", "", "", "Lpbandk/UnknownField;", "(Lcom/stremio/core/models/Player$Selected;Lcom/stremio/core/models/Player$VideoParams;Lcom/stremio/core/models/LoadableMetaItem;Ljava/util/List;Lcom/stremio/core/types/resource/Video;Lcom/stremio/core/types/resource/Video$SeriesInfo;Lcom/stremio/core/types/library/LibraryItem;Lcom/stremio/core/models/Player$StreamState;Lcom/stremio/core/models/Player$IntroOutro;Lcom/stremio/core/models/LoadableConvertedStream;Ljava/util/Map;)V", "descriptor", "Lpbandk/MessageDescriptor;", "getDescriptor", "()Lpbandk/MessageDescriptor;", "getIntroOutro", "()Lcom/stremio/core/models/Player$IntroOutro;", "getLibraryItem", "()Lcom/stremio/core/types/library/LibraryItem;", "getMetaItem", "()Lcom/stremio/core/models/LoadableMetaItem;", "getNextVideo", "()Lcom/stremio/core/types/resource/Video;", "protoSize", "getProtoSize", "()I", "protoSize$delegate", "Lkotlin/Lazy;", "getSelected", "()Lcom/stremio/core/models/Player$Selected;", "getSeriesInfo", "()Lcom/stremio/core/types/resource/Video$SeriesInfo;", "getStream", "()Lcom/stremio/core/models/LoadableConvertedStream;", "getStreamState", "()Lcom/stremio/core/models/Player$StreamState;", "getSubtitles", "()Ljava/util/List;", "getUnknownFields", "()Ljava/util/Map;", "getVideoParams", "()Lcom/stremio/core/models/Player$VideoParams;", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "", "hashCode", "plus", "toString", "", "AudioTrack", "Companion", "IntroData", "IntroOutro", "Selected", "StreamState", "SubtitleTrack", "VideoParams", "stremio-core-kotlin_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Export
public final /* data */ class Player implements Message {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static final Lazy<Player> defaultInstance$delegate;
    private static final MessageDescriptor<Player> descriptor;
    private final IntroOutro introOutro;
    private final LibraryItem libraryItem;
    private final LoadableMetaItem metaItem;
    private final Video nextVideo;

    /* JADX INFO: renamed from: protoSize$delegate, reason: from kotlin metadata */
    private final Lazy protoSize;
    private final Selected selected;
    private final Video.SeriesInfo seriesInfo;
    private final LoadableConvertedStream stream;
    private final StreamState streamState;
    private final List<LoadableSubtitles> subtitles;
    private final Map<Integer, UnknownField> unknownFields;
    private final VideoParams videoParams;

    public Player() {
        this(null, null, null, null, null, null, null, null, null, null, null, 2047, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Player copy$default(Player player, Selected selected, VideoParams videoParams, LoadableMetaItem loadableMetaItem, List list, Video video, Video.SeriesInfo seriesInfo, LibraryItem libraryItem, StreamState streamState, IntroOutro introOutro, LoadableConvertedStream loadableConvertedStream, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            selected = player.selected;
        }
        if ((i & 2) != 0) {
            videoParams = player.videoParams;
        }
        if ((i & 4) != 0) {
            loadableMetaItem = player.metaItem;
        }
        if ((i & 8) != 0) {
            list = player.subtitles;
        }
        if ((i & 16) != 0) {
            video = player.nextVideo;
        }
        if ((i & 32) != 0) {
            seriesInfo = player.seriesInfo;
        }
        if ((i & 64) != 0) {
            libraryItem = player.libraryItem;
        }
        if ((i & 128) != 0) {
            streamState = player.streamState;
        }
        if ((i & 256) != 0) {
            introOutro = player.introOutro;
        }
        if ((i & 512) != 0) {
            loadableConvertedStream = player.stream;
        }
        if ((i & 1024) != 0) {
            map = player.unknownFields;
        }
        LoadableConvertedStream loadableConvertedStream2 = loadableConvertedStream;
        Map map2 = map;
        StreamState streamState2 = streamState;
        IntroOutro introOutro2 = introOutro;
        Video.SeriesInfo seriesInfo2 = seriesInfo;
        LibraryItem libraryItem2 = libraryItem;
        Video video2 = video;
        LoadableMetaItem loadableMetaItem2 = loadableMetaItem;
        return player.copy(selected, videoParams, loadableMetaItem2, list, video2, seriesInfo2, libraryItem2, streamState2, introOutro2, loadableConvertedStream2, map2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Selected getSelected() {
        return this.selected;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final LoadableConvertedStream getStream() {
        return this.stream;
    }

    public final Map<Integer, UnknownField> component11() {
        return this.unknownFields;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final VideoParams getVideoParams() {
        return this.videoParams;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final LoadableMetaItem getMetaItem() {
        return this.metaItem;
    }

    public final List<LoadableSubtitles> component4() {
        return this.subtitles;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Video getNextVideo() {
        return this.nextVideo;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Video.SeriesInfo getSeriesInfo() {
        return this.seriesInfo;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final LibraryItem getLibraryItem() {
        return this.libraryItem;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final StreamState getStreamState() {
        return this.streamState;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final IntroOutro getIntroOutro() {
        return this.introOutro;
    }

    public final Player copy(Selected selected, VideoParams videoParams, LoadableMetaItem metaItem, List<LoadableSubtitles> subtitles, Video nextVideo, Video.SeriesInfo seriesInfo, LibraryItem libraryItem, StreamState streamState, IntroOutro introOutro, LoadableConvertedStream stream, Map<Integer, UnknownField> unknownFields) {
        Intrinsics.checkNotNullParameter(subtitles, "subtitles");
        Intrinsics.checkNotNullParameter(unknownFields, "unknownFields");
        return new Player(selected, videoParams, metaItem, subtitles, nextVideo, seriesInfo, libraryItem, streamState, introOutro, stream, unknownFields);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Player)) {
            return false;
        }
        Player player = (Player) other;
        return Intrinsics.areEqual(this.selected, player.selected) && Intrinsics.areEqual(this.videoParams, player.videoParams) && Intrinsics.areEqual(this.metaItem, player.metaItem) && Intrinsics.areEqual(this.subtitles, player.subtitles) && Intrinsics.areEqual(this.nextVideo, player.nextVideo) && Intrinsics.areEqual(this.seriesInfo, player.seriesInfo) && Intrinsics.areEqual(this.libraryItem, player.libraryItem) && Intrinsics.areEqual(this.streamState, player.streamState) && Intrinsics.areEqual(this.introOutro, player.introOutro) && Intrinsics.areEqual(this.stream, player.stream) && Intrinsics.areEqual(this.unknownFields, player.unknownFields);
    }

    public int hashCode() {
        Selected selected = this.selected;
        int iHashCode = (selected == null ? 0 : selected.hashCode()) * 31;
        VideoParams videoParams = this.videoParams;
        int iHashCode2 = (iHashCode + (videoParams == null ? 0 : videoParams.hashCode())) * 31;
        LoadableMetaItem loadableMetaItem = this.metaItem;
        int iHashCode3 = (((iHashCode2 + (loadableMetaItem == null ? 0 : loadableMetaItem.hashCode())) * 31) + this.subtitles.hashCode()) * 31;
        Video video = this.nextVideo;
        int iHashCode4 = (iHashCode3 + (video == null ? 0 : video.hashCode())) * 31;
        Video.SeriesInfo seriesInfo = this.seriesInfo;
        int iHashCode5 = (iHashCode4 + (seriesInfo == null ? 0 : seriesInfo.hashCode())) * 31;
        LibraryItem libraryItem = this.libraryItem;
        int iHashCode6 = (iHashCode5 + (libraryItem == null ? 0 : libraryItem.hashCode())) * 31;
        StreamState streamState = this.streamState;
        int iHashCode7 = (iHashCode6 + (streamState == null ? 0 : streamState.hashCode())) * 31;
        IntroOutro introOutro = this.introOutro;
        int iHashCode8 = (iHashCode7 + (introOutro == null ? 0 : introOutro.hashCode())) * 31;
        LoadableConvertedStream loadableConvertedStream = this.stream;
        return ((iHashCode8 + (loadableConvertedStream != null ? loadableConvertedStream.hashCode() : 0)) * 31) + this.unknownFields.hashCode();
    }

    public String toString() {
        return "Player(selected=" + this.selected + ", videoParams=" + this.videoParams + ", metaItem=" + this.metaItem + ", subtitles=" + this.subtitles + ", nextVideo=" + this.nextVideo + ", seriesInfo=" + this.seriesInfo + ", libraryItem=" + this.libraryItem + ", streamState=" + this.streamState + ", introOutro=" + this.introOutro + ", stream=" + this.stream + ", unknownFields=" + this.unknownFields + ")";
    }

    public Player(Selected selected, VideoParams videoParams, LoadableMetaItem loadableMetaItem, List<LoadableSubtitles> subtitles, Video video, Video.SeriesInfo seriesInfo, LibraryItem libraryItem, StreamState streamState, IntroOutro introOutro, LoadableConvertedStream loadableConvertedStream, Map<Integer, UnknownField> unknownFields) {
        Intrinsics.checkNotNullParameter(subtitles, "subtitles");
        Intrinsics.checkNotNullParameter(unknownFields, "unknownFields");
        this.selected = selected;
        this.videoParams = videoParams;
        this.metaItem = loadableMetaItem;
        this.subtitles = subtitles;
        this.nextVideo = video;
        this.seriesInfo = seriesInfo;
        this.libraryItem = libraryItem;
        this.streamState = streamState;
        this.introOutro = introOutro;
        this.stream = loadableConvertedStream;
        this.unknownFields = unknownFields;
        this.protoSize = LazyKt.lazy(new Function0<Integer>() { // from class: com.stremio.core.models.Player$protoSize$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(Message.DefaultImpls.getProtoSize(this.this$0));
            }
        });
    }

    public final Selected getSelected() {
        return this.selected;
    }

    public final VideoParams getVideoParams() {
        return this.videoParams;
    }

    public final LoadableMetaItem getMetaItem() {
        return this.metaItem;
    }

    public /* synthetic */ Player(Selected selected, VideoParams videoParams, LoadableMetaItem loadableMetaItem, List list, Video video, Video.SeriesInfo seriesInfo, LibraryItem libraryItem, StreamState streamState, IntroOutro introOutro, LoadableConvertedStream loadableConvertedStream, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : selected, (i & 2) != 0 ? null : videoParams, (i & 4) != 0 ? null : loadableMetaItem, (i & 8) != 0 ? CollectionsKt.emptyList() : list, (i & 16) != 0 ? null : video, (i & 32) != 0 ? null : seriesInfo, (i & 64) != 0 ? null : libraryItem, (i & 128) != 0 ? null : streamState, (i & 256) != 0 ? null : introOutro, (i & 512) != 0 ? null : loadableConvertedStream, (i & 1024) != 0 ? MapsKt.emptyMap() : map);
    }

    public final List<LoadableSubtitles> getSubtitles() {
        return this.subtitles;
    }

    public final Video getNextVideo() {
        return this.nextVideo;
    }

    public final Video.SeriesInfo getSeriesInfo() {
        return this.seriesInfo;
    }

    public final LibraryItem getLibraryItem() {
        return this.libraryItem;
    }

    public final StreamState getStreamState() {
        return this.streamState;
    }

    public final IntroOutro getIntroOutro() {
        return this.introOutro;
    }

    public final LoadableConvertedStream getStream() {
        return this.stream;
    }

    @Override // pbandk.Message
    public Map<Integer, UnknownField> getUnknownFields() {
        return this.unknownFields;
    }

    @Override // pbandk.Message
    public Player plus(Message other) {
        return PlayerKt.protoMergeImpl(this, other);
    }

    @Override // pbandk.Message
    public MessageDescriptor<Player> getDescriptor() {
        return descriptor;
    }

    /* JADX INFO: compiled from: player.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u001b\u0010\u0004\u001a\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0010"}, d2 = {"Lcom/stremio/core/models/Player$Companion;", "Lpbandk/Message$Companion;", "Lcom/stremio/core/models/Player;", "()V", "defaultInstance", "getDefaultInstance", "()Lcom/stremio/core/models/Player;", "defaultInstance$delegate", "Lkotlin/Lazy;", "descriptor", "Lpbandk/MessageDescriptor;", "getDescriptor", "()Lpbandk/MessageDescriptor;", "decodeWith", "u", "Lpbandk/MessageDecoder;", "stremio-core-kotlin_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion implements Message.Companion<Player> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Player getDefaultInstance() {
            return (Player) Player.defaultInstance$delegate.getValue();
        }

        @Override // pbandk.Message.Companion
        public Player decodeWith(MessageDecoder u) {
            Intrinsics.checkNotNullParameter(u, "u");
            return PlayerKt.decodeWithImpl(Player.INSTANCE, u);
        }

        @Override // pbandk.Message.Companion
        public MessageDescriptor<Player> getDescriptor() {
            return Player.descriptor;
        }
    }

    @Override // pbandk.Message
    public int getProtoSize() {
        return ((Number) this.protoSize.getValue()).intValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    static {
        final Companion companion = new Companion(null);
        INSTANCE = companion;
        defaultInstance$delegate = LazyKt.lazy(new Function0<Player>() { // from class: com.stremio.core.models.Player$Companion$defaultInstance$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Player invoke() {
                return new Player(null, null, null, null, null, null, null, null, null, null, null, 2047, null);
            }
        });
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Player.class);
        Companion companion2 = companion;
        List listCreateListBuilder = CollectionsKt.createListBuilder(10);
        int i = 2;
        listCreateListBuilder.add(new FieldDescriptor(new PropertyReference0Impl(companion) { // from class: com.stremio.core.models.Player$Companion$descriptor$1$1
            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                return ((Player.Companion) this.receiver).getDescriptor();
            }
        }, "selected", 1, new FieldDescriptor.Type.Message(Selected.INSTANCE, 0 == true ? 1 : 0, i, 0 == true ? 1 : 0), new PropertyReference1Impl() { // from class: com.stremio.core.models.Player$Companion$descriptor$1$2
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((Player) obj).getSelected();
            }
        }, false, "selected", null, 160, null));
        int i2 = 160;
        DefaultConstructorMarker defaultConstructorMarker = null;
        boolean z = false;
        FieldOptions fieldOptions = null;
        listCreateListBuilder.add(new FieldDescriptor(new PropertyReference0Impl(companion) { // from class: com.stremio.core.models.Player$Companion$descriptor$1$3
            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                return ((Player.Companion) this.receiver).getDescriptor();
            }
        }, "video_params", 2, new FieldDescriptor.Type.Message(VideoParams.INSTANCE, 0 == true ? 1 : 0, i, 0 == true ? 1 : 0), new PropertyReference1Impl() { // from class: com.stremio.core.models.Player$Companion$descriptor$1$4
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((Player) obj).getVideoParams();
            }
        }, z, "videoParams", fieldOptions, i2, defaultConstructorMarker));
        listCreateListBuilder.add(new FieldDescriptor(new PropertyReference0Impl(companion) { // from class: com.stremio.core.models.Player$Companion$descriptor$1$5
            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                return ((Player.Companion) this.receiver).getDescriptor();
            }
        }, "meta_item", 3, new FieldDescriptor.Type.Message(LoadableMetaItem.INSTANCE, 0 == true ? 1 : 0, i, 0 == true ? 1 : 0), new PropertyReference1Impl() { // from class: com.stremio.core.models.Player$Companion$descriptor$1$6
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((Player) obj).getMetaItem();
            }
        }, z, "metaItem", fieldOptions, i2, defaultConstructorMarker));
        listCreateListBuilder.add(new FieldDescriptor(new PropertyReference0Impl(companion) { // from class: com.stremio.core.models.Player$Companion$descriptor$1$7
            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                return ((Player.Companion) this.receiver).getDescriptor();
            }
        }, Tabs.SUBTITLES, 4, new FieldDescriptor.Type.Repeated(new FieldDescriptor.Type.Message(LoadableSubtitles.INSTANCE, 0 == true ? 1 : 0, i, 0 == true ? 1 : 0), false, i, 0 == true ? 1 : 0), new PropertyReference1Impl() { // from class: com.stremio.core.models.Player$Companion$descriptor$1$8
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((Player) obj).getSubtitles();
            }
        }, z, Tabs.SUBTITLES, fieldOptions, i2, defaultConstructorMarker));
        listCreateListBuilder.add(new FieldDescriptor(new PropertyReference0Impl(companion) { // from class: com.stremio.core.models.Player$Companion$descriptor$1$9
            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                return ((Player.Companion) this.receiver).getDescriptor();
            }
        }, "next_video", 5, new FieldDescriptor.Type.Message(Video.INSTANCE, 0 == true ? 1 : 0, i, 0 == true ? 1 : 0), new PropertyReference1Impl() { // from class: com.stremio.core.models.Player$Companion$descriptor$1$10
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((Player) obj).getNextVideo();
            }
        }, z, "nextVideo", fieldOptions, i2, defaultConstructorMarker));
        listCreateListBuilder.add(new FieldDescriptor(new PropertyReference0Impl(companion) { // from class: com.stremio.core.models.Player$Companion$descriptor$1$11
            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                return ((Player.Companion) this.receiver).getDescriptor();
            }
        }, "series_info", 6, new FieldDescriptor.Type.Message(Video.SeriesInfo.INSTANCE, 0 == true ? 1 : 0, i, 0 == true ? 1 : 0), new PropertyReference1Impl() { // from class: com.stremio.core.models.Player$Companion$descriptor$1$12
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((Player) obj).getSeriesInfo();
            }
        }, z, "seriesInfo", fieldOptions, i2, defaultConstructorMarker));
        listCreateListBuilder.add(new FieldDescriptor(new PropertyReference0Impl(companion) { // from class: com.stremio.core.models.Player$Companion$descriptor$1$13
            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                return ((Player.Companion) this.receiver).getDescriptor();
            }
        }, "library_item", 7, new FieldDescriptor.Type.Message(LibraryItem.INSTANCE, 0 == true ? 1 : 0, i, 0 == true ? 1 : 0), new PropertyReference1Impl() { // from class: com.stremio.core.models.Player$Companion$descriptor$1$14
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((Player) obj).getLibraryItem();
            }
        }, z, "libraryItem", fieldOptions, i2, defaultConstructorMarker));
        listCreateListBuilder.add(new FieldDescriptor(new PropertyReference0Impl(companion) { // from class: com.stremio.core.models.Player$Companion$descriptor$1$15
            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                return ((Player.Companion) this.receiver).getDescriptor();
            }
        }, "stream_state", 8, new FieldDescriptor.Type.Message(StreamState.INSTANCE, 0 == true ? 1 : 0, i, 0 == true ? 1 : 0), new PropertyReference1Impl() { // from class: com.stremio.core.models.Player$Companion$descriptor$1$16
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((Player) obj).getStreamState();
            }
        }, z, "streamState", fieldOptions, i2, defaultConstructorMarker));
        listCreateListBuilder.add(new FieldDescriptor(new PropertyReference0Impl(companion) { // from class: com.stremio.core.models.Player$Companion$descriptor$1$17
            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                return ((Player.Companion) this.receiver).getDescriptor();
            }
        }, "intro_outro", 9, new FieldDescriptor.Type.Message(IntroOutro.INSTANCE, 0 == true ? 1 : 0, i, 0 == true ? 1 : 0), new PropertyReference1Impl() { // from class: com.stremio.core.models.Player$Companion$descriptor$1$18
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((Player) obj).getIntroOutro();
            }
        }, z, "introOutro", fieldOptions, i2, defaultConstructorMarker));
        listCreateListBuilder.add(new FieldDescriptor(new PropertyReference0Impl(companion) { // from class: com.stremio.core.models.Player$Companion$descriptor$1$19
            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                return ((Player.Companion) this.receiver).getDescriptor();
            }
        }, "stream", 10, new FieldDescriptor.Type.Message(LoadableConvertedStream.INSTANCE, 0 == true ? 1 : 0, i, 0 == true ? 1 : 0), new PropertyReference1Impl() { // from class: com.stremio.core.models.Player$Companion$descriptor$1$20
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((Player) obj).getStream();
            }
        }, z, "stream", fieldOptions, i2, defaultConstructorMarker));
        Unit unit = Unit.INSTANCE;
        descriptor = new MessageDescriptor<>("stremio.core.models.Player", orCreateKotlinClass, companion2, CollectionsKt.build(listCreateListBuilder));
    }

    /* JADX INFO: compiled from: player.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0086\b\u0018\u0000 *2\u00020\u0001:\u0001*B?\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0019J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bHÆ\u0003JH\u0010!\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bHÆ\u0001¢\u0006\u0002\u0010\"J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&HÖ\u0003J\t\u0010'\u001a\u00020\tHÖ\u0001J\u0013\u0010(\u001a\u00020\u00002\b\u0010%\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\t\u0010)\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00000\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u001b\u0010\u0013\u001a\u00020\t8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019R \u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c¨\u0006+"}, d2 = {"Lcom/stremio/core/models/Player$VideoParams;", "Lpbandk/Message;", "hash", "", ContentDisposition.Parameters.Size, "", ContentDisposition.Parameters.FileName, "unknownFields", "", "", "Lpbandk/UnknownField;", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/util/Map;)V", "descriptor", "Lpbandk/MessageDescriptor;", "getDescriptor", "()Lpbandk/MessageDescriptor;", "getFilename", "()Ljava/lang/String;", "getHash", "protoSize", "getProtoSize", "()I", "protoSize$delegate", "Lkotlin/Lazy;", "getSize", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getUnknownFields", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/util/Map;)Lcom/stremio/core/models/Player$VideoParams;", "equals", "", "other", "", "hashCode", "plus", "toString", "Companion", "stremio-core-kotlin_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class VideoParams implements Message {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE;
        private static final Lazy<VideoParams> defaultInstance$delegate;
        private static final MessageDescriptor<VideoParams> descriptor;
        private final String filename;
        private final String hash;

        /* JADX INFO: renamed from: protoSize$delegate, reason: from kotlin metadata */
        private final Lazy protoSize;
        private final Long size;
        private final Map<Integer, UnknownField> unknownFields;

        public VideoParams() {
            this(null, null, null, null, 15, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ VideoParams copy$default(VideoParams videoParams, String str, Long l, String str2, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                str = videoParams.hash;
            }
            if ((i & 2) != 0) {
                l = videoParams.size;
            }
            if ((i & 4) != 0) {
                str2 = videoParams.filename;
            }
            if ((i & 8) != 0) {
                map = videoParams.unknownFields;
            }
            return videoParams.copy(str, l, str2, map);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getHash() {
            return this.hash;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Long getSize() {
            return this.size;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getFilename() {
            return this.filename;
        }

        public final Map<Integer, UnknownField> component4() {
            return this.unknownFields;
        }

        public final VideoParams copy(String hash, Long size, String filename, Map<Integer, UnknownField> unknownFields) {
            Intrinsics.checkNotNullParameter(unknownFields, "unknownFields");
            return new VideoParams(hash, size, filename, unknownFields);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VideoParams)) {
                return false;
            }
            VideoParams videoParams = (VideoParams) other;
            return Intrinsics.areEqual(this.hash, videoParams.hash) && Intrinsics.areEqual(this.size, videoParams.size) && Intrinsics.areEqual(this.filename, videoParams.filename) && Intrinsics.areEqual(this.unknownFields, videoParams.unknownFields);
        }

        public int hashCode() {
            String str = this.hash;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            Long l = this.size;
            int iHashCode2 = (iHashCode + (l == null ? 0 : l.hashCode())) * 31;
            String str2 = this.filename;
            return ((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + this.unknownFields.hashCode();
        }

        public String toString() {
            return "VideoParams(hash=" + this.hash + ", size=" + this.size + ", filename=" + this.filename + ", unknownFields=" + this.unknownFields + ")";
        }

        public VideoParams(String str, Long l, String str2, Map<Integer, UnknownField> unknownFields) {
            Intrinsics.checkNotNullParameter(unknownFields, "unknownFields");
            this.hash = str;
            this.size = l;
            this.filename = str2;
            this.unknownFields = unknownFields;
            this.protoSize = LazyKt.lazy(new Function0<Integer>() { // from class: com.stremio.core.models.Player$VideoParams$protoSize$2
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    return Integer.valueOf(Message.DefaultImpls.getProtoSize(this.this$0));
                }
            });
        }

        public final String getHash() {
            return this.hash;
        }

        public final Long getSize() {
            return this.size;
        }

        public final String getFilename() {
            return this.filename;
        }

        public /* synthetic */ VideoParams(String str, Long l, String str2, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : l, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? MapsKt.emptyMap() : map);
        }

        @Override // pbandk.Message
        public Map<Integer, UnknownField> getUnknownFields() {
            return this.unknownFields;
        }

        @Override // pbandk.Message
        public VideoParams plus(Message other) {
            return PlayerKt.protoMergeImpl(this, other);
        }

        @Override // pbandk.Message
        public MessageDescriptor<VideoParams> getDescriptor() {
            return descriptor;
        }

        /* JADX INFO: compiled from: player.kt */
        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u001b\u0010\u0004\u001a\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0010"}, d2 = {"Lcom/stremio/core/models/Player$VideoParams$Companion;", "Lpbandk/Message$Companion;", "Lcom/stremio/core/models/Player$VideoParams;", "()V", "defaultInstance", "getDefaultInstance", "()Lcom/stremio/core/models/Player$VideoParams;", "defaultInstance$delegate", "Lkotlin/Lazy;", "descriptor", "Lpbandk/MessageDescriptor;", "getDescriptor", "()Lpbandk/MessageDescriptor;", "decodeWith", "u", "Lpbandk/MessageDecoder;", "stremio-core-kotlin_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion implements Message.Companion<VideoParams> {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final VideoParams getDefaultInstance() {
                return (VideoParams) VideoParams.defaultInstance$delegate.getValue();
            }

            @Override // pbandk.Message.Companion
            public VideoParams decodeWith(MessageDecoder u) {
                Intrinsics.checkNotNullParameter(u, "u");
                return PlayerKt.decodeWithImpl(VideoParams.INSTANCE, u);
            }

            @Override // pbandk.Message.Companion
            public MessageDescriptor<VideoParams> getDescriptor() {
                return VideoParams.descriptor;
            }
        }

        @Override // pbandk.Message
        public int getProtoSize() {
            return ((Number) this.protoSize.getValue()).intValue();
        }

        static {
            final Companion companion = new Companion(null);
            INSTANCE = companion;
            defaultInstance$delegate = LazyKt.lazy(new Function0<VideoParams>() { // from class: com.stremio.core.models.Player$VideoParams$Companion$defaultInstance$2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Player.VideoParams invoke() {
                    return new Player.VideoParams(null, null, null, null, 15, null);
                }
            });
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(VideoParams.class);
            Companion companion2 = companion;
            List listCreateListBuilder = CollectionsKt.createListBuilder(3);
            listCreateListBuilder.add(new FieldDescriptor(new PropertyReference0Impl(companion) { // from class: com.stremio.core.models.Player$VideoParams$Companion$descriptor$1$1
                @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
                public Object get() {
                    return ((Player.VideoParams.Companion) this.receiver).getDescriptor();
                }
            }, "hash", 1, new FieldDescriptor.Type.Primitive.String(true), new PropertyReference1Impl() { // from class: com.stremio.core.models.Player$VideoParams$Companion$descriptor$1$2
                @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
                public Object get(Object obj) {
                    return ((Player.VideoParams) obj).getHash();
                }
            }, false, "hash", null, 160, null));
            int i = 160;
            DefaultConstructorMarker defaultConstructorMarker = null;
            boolean z = false;
            FieldOptions fieldOptions = null;
            listCreateListBuilder.add(new FieldDescriptor(new PropertyReference0Impl(companion) { // from class: com.stremio.core.models.Player$VideoParams$Companion$descriptor$1$3
                @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
                public Object get() {
                    return ((Player.VideoParams.Companion) this.receiver).getDescriptor();
                }
            }, ContentDisposition.Parameters.Size, 2, new FieldDescriptor.Type.Primitive.UInt64(true), new PropertyReference1Impl() { // from class: com.stremio.core.models.Player$VideoParams$Companion$descriptor$1$4
                @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
                public Object get(Object obj) {
                    return ((Player.VideoParams) obj).getSize();
                }
            }, z, ContentDisposition.Parameters.Size, fieldOptions, i, defaultConstructorMarker));
            listCreateListBuilder.add(new FieldDescriptor(new PropertyReference0Impl(companion) { // from class: com.stremio.core.models.Player$VideoParams$Companion$descriptor$1$5
                @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
                public Object get() {
                    return ((Player.VideoParams.Companion) this.receiver).getDescriptor();
                }
            }, ContentDisposition.Parameters.FileName, 3, new FieldDescriptor.Type.Primitive.String(true), new PropertyReference1Impl() { // from class: com.stremio.core.models.Player$VideoParams$Companion$descriptor$1$6
                @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
                public Object get(Object obj) {
                    return ((Player.VideoParams) obj).getFilename();
                }
            }, z, ContentDisposition.Parameters.FileName, fieldOptions, i, defaultConstructorMarker));
            Unit unit = Unit.INSTANCE;
            descriptor = new MessageDescriptor<>("stremio.core.models.Player.VideoParams", orCreateKotlinClass, companion2, CollectionsKt.build(listCreateListBuilder));
        }
    }

    /* JADX INFO: compiled from: player.kt */
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0086\b\u0018\u0000 @2\u00020\u0001:\u0001@B{\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\u0014\b\u0002\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010¢\u0006\u0002\u0010\u0013J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010/\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0015J\u0010\u00100\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001eJ\u0010\u00101\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001eJ\u000b\u00102\u001a\u0004\u0018\u00010\nHÆ\u0003J\u0010\u00103\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0015J\u0010\u00104\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001eJ\u000b\u00105\u001a\u0004\u0018\u00010\u000eHÆ\u0003J\u0015\u00106\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010HÆ\u0003J\u0084\u0001\u00107\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0014\b\u0002\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010HÆ\u0001¢\u0006\u0002\u00108J\u0013\u00109\u001a\u00020:2\b\u0010;\u001a\u0004\u0018\u00010<HÖ\u0003J\t\u0010=\u001a\u00020\u0011HÖ\u0001J\u0013\u0010>\u001a\u00020\u00002\b\u0010;\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\t\u0010?\u001a\u00020\u000eHÖ\u0001R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00000\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0015\u0010\f\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u001b\u0010\"\u001a\u00020\u00118VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b#\u0010$R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b'\u0010\u0015R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b(\u0010\u001eR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b)\u0010\u001eR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R \u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-¨\u0006A"}, d2 = {"Lcom/stremio/core/models/Player$StreamState;", "Lpbandk/Message;", "subtitleTrack", "Lcom/stremio/core/models/Player$SubtitleTrack;", "subtitleDelay", "", "subtitleSize", "", "subtitleOffset", "audioTrack", "Lcom/stremio/core/models/Player$AudioTrack;", "audioDelay", "playbackSpeed", "playerType", "", "unknownFields", "", "", "Lpbandk/UnknownField;", "(Lcom/stremio/core/models/Player$SubtitleTrack;Ljava/lang/Long;Ljava/lang/Float;Ljava/lang/Float;Lcom/stremio/core/models/Player$AudioTrack;Ljava/lang/Long;Ljava/lang/Float;Ljava/lang/String;Ljava/util/Map;)V", "getAudioDelay", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getAudioTrack", "()Lcom/stremio/core/models/Player$AudioTrack;", "descriptor", "Lpbandk/MessageDescriptor;", "getDescriptor", "()Lpbandk/MessageDescriptor;", "getPlaybackSpeed", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getPlayerType", "()Ljava/lang/String;", "protoSize", "getProtoSize", "()I", "protoSize$delegate", "Lkotlin/Lazy;", "getSubtitleDelay", "getSubtitleOffset", "getSubtitleSize", "getSubtitleTrack", "()Lcom/stremio/core/models/Player$SubtitleTrack;", "getUnknownFields", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Lcom/stremio/core/models/Player$SubtitleTrack;Ljava/lang/Long;Ljava/lang/Float;Ljava/lang/Float;Lcom/stremio/core/models/Player$AudioTrack;Ljava/lang/Long;Ljava/lang/Float;Ljava/lang/String;Ljava/util/Map;)Lcom/stremio/core/models/Player$StreamState;", "equals", "", "other", "", "hashCode", "plus", "toString", "Companion", "stremio-core-kotlin_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class StreamState implements Message {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE;
        private static final Lazy<StreamState> defaultInstance$delegate;
        private static final MessageDescriptor<StreamState> descriptor;
        private final Long audioDelay;
        private final AudioTrack audioTrack;
        private final Float playbackSpeed;
        private final String playerType;

        /* JADX INFO: renamed from: protoSize$delegate, reason: from kotlin metadata */
        private final Lazy protoSize;
        private final Long subtitleDelay;
        private final Float subtitleOffset;
        private final Float subtitleSize;
        private final SubtitleTrack subtitleTrack;
        private final Map<Integer, UnknownField> unknownFields;

        public StreamState() {
            this(null, null, null, null, null, null, null, null, null, FrameMetricsAggregator.EVERY_DURATION, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ StreamState copy$default(StreamState streamState, SubtitleTrack subtitleTrack, Long l, Float f, Float f2, AudioTrack audioTrack, Long l2, Float f3, String str, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                subtitleTrack = streamState.subtitleTrack;
            }
            if ((i & 2) != 0) {
                l = streamState.subtitleDelay;
            }
            if ((i & 4) != 0) {
                f = streamState.subtitleSize;
            }
            if ((i & 8) != 0) {
                f2 = streamState.subtitleOffset;
            }
            if ((i & 16) != 0) {
                audioTrack = streamState.audioTrack;
            }
            if ((i & 32) != 0) {
                l2 = streamState.audioDelay;
            }
            if ((i & 64) != 0) {
                f3 = streamState.playbackSpeed;
            }
            if ((i & 128) != 0) {
                str = streamState.playerType;
            }
            if ((i & 256) != 0) {
                map = streamState.unknownFields;
            }
            String str2 = str;
            Map map2 = map;
            Long l3 = l2;
            Float f4 = f3;
            AudioTrack audioTrack2 = audioTrack;
            Float f5 = f;
            return streamState.copy(subtitleTrack, l, f5, f2, audioTrack2, l3, f4, str2, map2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final SubtitleTrack getSubtitleTrack() {
            return this.subtitleTrack;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Long getSubtitleDelay() {
            return this.subtitleDelay;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Float getSubtitleSize() {
            return this.subtitleSize;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Float getSubtitleOffset() {
            return this.subtitleOffset;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final AudioTrack getAudioTrack() {
            return this.audioTrack;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Long getAudioDelay() {
            return this.audioDelay;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Float getPlaybackSpeed() {
            return this.playbackSpeed;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getPlayerType() {
            return this.playerType;
        }

        public final Map<Integer, UnknownField> component9() {
            return this.unknownFields;
        }

        public final StreamState copy(SubtitleTrack subtitleTrack, Long subtitleDelay, Float subtitleSize, Float subtitleOffset, AudioTrack audioTrack, Long audioDelay, Float playbackSpeed, String playerType, Map<Integer, UnknownField> unknownFields) {
            Intrinsics.checkNotNullParameter(unknownFields, "unknownFields");
            return new StreamState(subtitleTrack, subtitleDelay, subtitleSize, subtitleOffset, audioTrack, audioDelay, playbackSpeed, playerType, unknownFields);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StreamState)) {
                return false;
            }
            StreamState streamState = (StreamState) other;
            return Intrinsics.areEqual(this.subtitleTrack, streamState.subtitleTrack) && Intrinsics.areEqual(this.subtitleDelay, streamState.subtitleDelay) && Intrinsics.areEqual((Object) this.subtitleSize, (Object) streamState.subtitleSize) && Intrinsics.areEqual((Object) this.subtitleOffset, (Object) streamState.subtitleOffset) && Intrinsics.areEqual(this.audioTrack, streamState.audioTrack) && Intrinsics.areEqual(this.audioDelay, streamState.audioDelay) && Intrinsics.areEqual((Object) this.playbackSpeed, (Object) streamState.playbackSpeed) && Intrinsics.areEqual(this.playerType, streamState.playerType) && Intrinsics.areEqual(this.unknownFields, streamState.unknownFields);
        }

        public int hashCode() {
            SubtitleTrack subtitleTrack = this.subtitleTrack;
            int iHashCode = (subtitleTrack == null ? 0 : subtitleTrack.hashCode()) * 31;
            Long l = this.subtitleDelay;
            int iHashCode2 = (iHashCode + (l == null ? 0 : l.hashCode())) * 31;
            Float f = this.subtitleSize;
            int iHashCode3 = (iHashCode2 + (f == null ? 0 : f.hashCode())) * 31;
            Float f2 = this.subtitleOffset;
            int iHashCode4 = (iHashCode3 + (f2 == null ? 0 : f2.hashCode())) * 31;
            AudioTrack audioTrack = this.audioTrack;
            int iHashCode5 = (iHashCode4 + (audioTrack == null ? 0 : audioTrack.hashCode())) * 31;
            Long l2 = this.audioDelay;
            int iHashCode6 = (iHashCode5 + (l2 == null ? 0 : l2.hashCode())) * 31;
            Float f3 = this.playbackSpeed;
            int iHashCode7 = (iHashCode6 + (f3 == null ? 0 : f3.hashCode())) * 31;
            String str = this.playerType;
            return ((iHashCode7 + (str != null ? str.hashCode() : 0)) * 31) + this.unknownFields.hashCode();
        }

        public String toString() {
            return "StreamState(subtitleTrack=" + this.subtitleTrack + ", subtitleDelay=" + this.subtitleDelay + ", subtitleSize=" + this.subtitleSize + ", subtitleOffset=" + this.subtitleOffset + ", audioTrack=" + this.audioTrack + ", audioDelay=" + this.audioDelay + ", playbackSpeed=" + this.playbackSpeed + ", playerType=" + this.playerType + ", unknownFields=" + this.unknownFields + ")";
        }

        public StreamState(SubtitleTrack subtitleTrack, Long l, Float f, Float f2, AudioTrack audioTrack, Long l2, Float f3, String str, Map<Integer, UnknownField> unknownFields) {
            Intrinsics.checkNotNullParameter(unknownFields, "unknownFields");
            this.subtitleTrack = subtitleTrack;
            this.subtitleDelay = l;
            this.subtitleSize = f;
            this.subtitleOffset = f2;
            this.audioTrack = audioTrack;
            this.audioDelay = l2;
            this.playbackSpeed = f3;
            this.playerType = str;
            this.unknownFields = unknownFields;


... [truncated: exceeds 60000 bytes, total 141760 bytes]