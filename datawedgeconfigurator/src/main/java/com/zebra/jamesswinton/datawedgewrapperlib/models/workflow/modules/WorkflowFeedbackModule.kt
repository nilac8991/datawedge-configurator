package com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.modules

data class WorkflowFeedbackModule(
        var decodeHapticFeedback: Boolean = false,
        var decodeAudioFeedbackUri: String = "Heaven",
        var volumeSliderType: VolumeSliderType = VolumeSliderType.MUSIC_AND_MEDIA
) {
    enum class VolumeSliderType {
        RINGER, MUSIC_AND_MEDIA, ALARMS, NOTIFICATION;
    }
}