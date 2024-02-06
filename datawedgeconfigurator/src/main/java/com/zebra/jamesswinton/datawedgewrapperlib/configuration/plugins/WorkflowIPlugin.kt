package com.zebra.jamesswinton.datawedgewrapperlib.configuration.plugins

import android.os.Bundle
import com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.WorkflowInputMode
import com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.WorkflowMode
import com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.modules.WorkflowCameraModule
import com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.modules.WorkflowContainerDecoderModule
import com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.modules.WorkflowFeedbackModule
import com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.modules.WorkflowFreeFormCaptureDecoderModule
import com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.modules.WorkflowIdentificationDecoderModule
import com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.modules.WorkflowLicenseDecoderModule
import com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.modules.WorkflowMeterDecoderModule
import com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.modules.WorkflowTINDecoderModule
import com.zebra.jamesswinton.datawedgewrapperlib.models.workflow.modules.WorkflowVINDecoderModule

open class WorkflowIPlugin private constructor(builder: Builder) {

    private val plugin = Bundle()

    init {
        // Build Params
        val paramList = Bundle().apply {
            putString(WORKFLOW_NAME, builder.mWorkflowMode.mode)
            putString(WORKFLOW_INPUT_SOURCE, (builder.mWorkflowInputMode.ordinal + 1).toString())
            putParcelableArrayList(WORKFLOW_PARAMS, builder.workflowParams)
        }

        // Build Plugin
        plugin.putString("PLUGIN_NAME", PLUGIN_NAME)
        plugin.putString("RESET_CONFIG", if (builder.resetConfig) "true" else "false")
        plugin.putString(WORKFLOW_ENABLED_KEY, if (builder.pluginEnabled) "true" else "false")
        plugin.putString(WORKFLOW_SELECTED_MODE, builder.mWorkflowMode.mode)
        plugin.putString(WORKFLOW_INPUT_SOURCE, (builder.mWorkflowInputMode.ordinal + 1).toString())
        plugin.putParcelableArrayList("PARAM_LIST", arrayListOf(paramList))
    }

    class Builder {

        // Config
        internal var resetConfig = true
        internal var workflowParams: ArrayList<Bundle> = ArrayList()

        //Params
        internal var pluginEnabled: Boolean = false
        internal var mWorkflowMode: WorkflowMode = WorkflowMode.LICENSE_PLATE
        internal var mWorkflowInputMode: WorkflowInputMode = WorkflowInputMode.CAMERA

        //Generic Modules
        private var mCameraModule: WorkflowCameraModule = WorkflowCameraModule()
        private var mCameraModuleBundle: Bundle = Bundle()

        private var mFeedbackModule: WorkflowFeedbackModule = WorkflowFeedbackModule()
        private var mFeedbackModuleBundle: Bundle = Bundle()

        //License Module
        private var licenseDecoderModuleBundle: Bundle = Bundle()
        private var mLicenseDecoderModule: WorkflowLicenseDecoderModule =
            WorkflowLicenseDecoderModule()

        //Identification
        private var identificationDecoderModuleBundle: Bundle = Bundle()
        private var mIdentificationDecoderModule: WorkflowIdentificationDecoderModule =
            WorkflowIdentificationDecoderModule()

        //VIN
        private var vinDecoderModuleBundle: Bundle = Bundle()
        private var mVinDecoderModule: WorkflowVINDecoderModule = WorkflowVINDecoderModule()

        //TIN
        private var tinDecoderModuleBundle: Bundle = Bundle()
        private var mTinDecoderModule: WorkflowTINDecoderModule = WorkflowTINDecoderModule()

        //Meter
        private var meterDecoderModuleBundle: Bundle = Bundle()
        private var mMeterDecoderModule: WorkflowMeterDecoderModule = WorkflowMeterDecoderModule()

        //Container
        private var containerDecoderModuleBundle: Bundle = Bundle()
        private var mContainerDecoderModule: WorkflowContainerDecoderModule =
            WorkflowContainerDecoderModule()

        //Free Form Capture
        private var freeFormCaptureDecoderModuleBundle: Bundle = Bundle()
        private var mFreeFormCaptureDecoderModule: WorkflowFreeFormCaptureDecoderModule =
            WorkflowFreeFormCaptureDecoderModule()

        fun resetConfig(resetConfig: Boolean): Builder {
            this.resetConfig = resetConfig
            return this
        }

        fun setEnabled(enabled: Boolean): Builder {
            this.pluginEnabled = enabled
            return this
        }

        fun setWorkflowMode(mode: WorkflowMode): Builder {
            this.mWorkflowMode = mode
            return this
        }

        fun setWorkflowInputMode(inputMode: WorkflowInputMode): Builder {
            this.mWorkflowInputMode = inputMode
            return this
        }

        fun setCameraModule(cameraModule: WorkflowCameraModule): Builder {
            this.mCameraModule = cameraModule
            return this
        }

        fun setFeedbackModule(feedbackModule: WorkflowFeedbackModule): Builder {
            this.mFeedbackModule = feedbackModule
            return this
        }

        fun setLicenseDecoderModule(licenseDecoderModule: WorkflowLicenseDecoderModule): Builder {
            this.mLicenseDecoderModule = licenseDecoderModule
            return this
        }

        fun setIdentificationDecoderModule(identificationDecoderModule: WorkflowIdentificationDecoderModule): Builder {
            this.mIdentificationDecoderModule = identificationDecoderModule
            return this
        }

        fun setVINDecoderModule(vinDecoderModule: WorkflowVINDecoderModule): Builder {
            this.mVinDecoderModule = vinDecoderModule
            return this
        }

        fun setTINDecoderModule(tinDecoderModule: WorkflowTINDecoderModule): Builder {
            this.mTinDecoderModule = tinDecoderModule
            return this
        }

        fun setMeterDecoderModule(meterDecoderModule: WorkflowMeterDecoderModule): Builder {
            this.mMeterDecoderModule = meterDecoderModule
            return this
        }

        fun setContainerDecoderModule(containerDecoderModule: WorkflowContainerDecoderModule): Builder {
            this.mContainerDecoderModule = containerDecoderModule
            return this
        }

        fun setFreeFormCaptureModule(freeFormCaptureDecoderModule: WorkflowFreeFormCaptureDecoderModule): Builder {
            this.mFreeFormCaptureDecoderModule = freeFormCaptureDecoderModule
            return this
        }

        fun create(): Bundle {
            licenseDecoderModuleBundle.apply {
                putString("module", mLicenseDecoderModule.name)
                putBundle("module_params", Bundle().apply {
                    putString("session_timeout", mLicenseDecoderModule.sessionTimeOut.toString())
                    putString("output_image", mLicenseDecoderModule.outputImage.mode)
                    putString("scanMode", mLicenseDecoderModule.scanMode.mode)
                })
            }

            identificationDecoderModuleBundle.apply {
                putString("module", mIdentificationDecoderModule.name)
                putBundle("module_params", Bundle().apply {
                    putString(
                        "session_timeout",
                        mIdentificationDecoderModule.sessionTimeOut.toString()
                    )
                    putString("output_image", mIdentificationDecoderModule.outputImage.mode)
                })
            }

            vinDecoderModuleBundle.apply {
                putString("module", mVinDecoderModule.name)
                putBundle("module_params", Bundle().apply {
                    putString("session_timeout", mVinDecoderModule.sessionTimeOut.toString())
                    putString("output_image", mVinDecoderModule.outputImage.mode)
                })
            }

            tinDecoderModuleBundle.apply {
                putString("module", mTinDecoderModule.name)
                putBundle("module_params", Bundle().apply {
                    putString("session_timeout", mTinDecoderModule.sessionTimeOut.toString())
                    putString("output_image", mTinDecoderModule.outputImage.mode)
                    putString("scanMode", mTinDecoderModule.scanMode.mode)
                })
            }

            meterDecoderModuleBundle.apply {
                putString("module", mMeterDecoderModule.name)
                putBundle("module_params", Bundle().apply {
                    putString("session_timeout", mMeterDecoderModule.sessionTimeOut.toString())
                    putString("output_image", mMeterDecoderModule.outputImage.mode)
                    putString("scanMode", mMeterDecoderModule.scanMode.mode)
                })
            }

            containerDecoderModuleBundle.apply {
                putString("module", mContainerDecoderModule.name)
                putBundle("module_params", Bundle().apply {
                    putString("orientation", mContainerDecoderModule.orientation.type)
                    putString("output_image", mMeterDecoderModule.outputImage.mode)
                })
            }

            freeFormCaptureDecoderModuleBundle.apply {
                putString("module", mFreeFormCaptureDecoderModule.name)
                putBundle("module_params", Bundle().apply {
                    putString(
                        "session_timeout",
                        mFreeFormCaptureDecoderModule.sessionTimeOut.toString()
                    )
                    putString(
                        "decode_and_highlight_barcodes",
                        (mFreeFormCaptureDecoderModule.mode.ordinal + 1).toString()
                    )
                })
            }

            mCameraModuleBundle.apply {
                putString("module", "CameraModule")
                putBundle("module_params", Bundle().apply {
                    putString("illumination", mCameraModule.illumination.mode)
                    putString("zoom", mCameraModule.zoom.toString())
                })
            }

            mFeedbackModuleBundle.apply {
                putString("module", "FeedbackModule")
                putBundle("module_params", Bundle().apply {
                    putString(
                        "decode_haptic_feedback",
                        mFeedbackModule.decodeHapticFeedback.toString()
                    )
                    putString("decode_audio_feedback_uri", mFeedbackModule.decodeAudioFeedbackUri)
                    putString(
                        "volume_slider_type",
                        mFeedbackModule.volumeSliderType.ordinal.toString()
                    )
                })
            }

            workflowParams.apply {
                when (mWorkflowMode) {
                    WorkflowMode.LICENSE_PLATE -> {
                        add(licenseDecoderModuleBundle)
                    }

                    WorkflowMode.ID -> {
                        add(identificationDecoderModuleBundle)
                    }

                    WorkflowMode.VIN -> {
                        add(vinDecoderModuleBundle)
                    }

                    WorkflowMode.TIN -> {
                        add(tinDecoderModuleBundle)
                    }

                    WorkflowMode.METER -> {
                        add(meterDecoderModuleBundle)
                    }

                    WorkflowMode.CONTAINER -> {
                        add(containerDecoderModuleBundle)
                    }

                    WorkflowMode.FREE_FORM_CAPTURE -> {
                        add(freeFormCaptureDecoderModuleBundle)
                    }

                    else -> {}
                }
                add(mCameraModuleBundle)
                add(mFeedbackModuleBundle)
            }

            return WorkflowIPlugin(this).plugin
        }
    }

    companion object {
        private const val TAG = "WorkflowPlugin"

        //Workflow Generic Constants
        private const val PLUGIN_NAME = "WORKFLOW"
        private const val WORKFLOW_ENABLED_KEY = "workflow_input_enabled"
        private const val WORKFLOW_NAME = "workflow_name"
        private const val WORKFLOW_SELECTED_MODE = "selected_workflow_name"
        private const val WORKFLOW_INPUT_SOURCE = "workflow_input_source"
        private const val WORKFLOW_PARAMS = "workflow_params"
    }
}