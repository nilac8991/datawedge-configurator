# DataWedge Configurator

> Library meant to simplify the usage of DataWedge across Zebra devices by using a method oriented approach to setup different aspects of a profile instead of working with multiple nested bunldes which could get messy.

[![](https://jitpack.io/v/nilac8991/datawedge-configurator.svg)](https://jitpack.io/#nilac8991/datawedge-configurator) [![](https://jitpack.io/v/nilac8991/datawedge-configurator/month.svg)](https://jitpack.io/#nilac8991/datawedge-configurator)

## How does it work?
The way this library works is very simple, it is based on builders meaning that you can create and apply configurations for a specific profile just by using methods instead of working with complicated nested bundles.
We simplify as much as possible the interface for you and we take care of the rest. It will provide an easier and flexible way of working with DataWedge and also save a good amount of lines of code.
<br>
<br>
This requires that you already know how DataWedge works on the Zebra devices. If you don't know anything about it please visit the official documentation on TechDocs [here](https://techdocs.zebra.com/datawedge/latest/guide/gettingstarted/) and get familiar with it.

## Installation

Top level build.gradle
```groovy
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
  }
}
```

Module level build.gradle
```groovy
dependencies {
  implementation "com.github.nilac8991:datawedge-configurator:1.7.3"
}
```

> [!warning]  
<b>Please be aware that this library is NOT officially supported by Zebra and it is distributed as is without any guarantee of "eternal" support or updates. I'll do my best to keep it updated as long as I can, so if you plan to use it inside your project remember that this is your choice and you understand the risks.</b>


## Status & Support
DataWedge is very big in terms on how many features and parameters it offers, which is also why it took quite some time to map everything into this library.
<br>
You will find available most of the features that developers typically use when working traditionally with DataWedge on Zebra devices.

### Plugins

|             Plugin             |                         Status                         | Notes                                                                                                                                                                                                                                                            |
|:------------------------------:|:------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|            Barcode             | ${{\color{Dandelion}{\textsf{Partially Supported}}}}\$ | - NextGen SimulScan Parameters are not supported<br>- UDI Parameters are not supported<br>- OCR Parameters are not supported<br>- Option to enable/disable symbologies is supported but there's no current support to personalise params of a specific symbology |
|  Magnetic Stripe Reader (MSR)  |    ${{\color{BrickRed}{\textsf{Not Supported}}}}\$     |                                                                                                                                                                                                                                                                  |
|              RFID              |    ${{\color{BrickRed}{\textsf{Not Supported}}}}\$     | Planned to be added soon                                                                                                                                                                                                                                         |
|             SERIAL             |    ${{\color{BrickRed}{\textsf{Not Supported}}}}\$     |                                                                                                                                                                                                                                                                  |
|             VOICE              |  ${{\color{ForestGreen}{\textsf{Fully Supported}}}}\$  |                                                                                                                                                                                                                                                                  |
|            WORKFLOW            |  ${{\color{ForestGreen}{\textsf{Fully Supported}}}}\$  |                                                                                                                                                                                                                                                                  |
|  Basic Data Formatting (BDF)   |  ${{\color{ForestGreen}{\textsf{Fully Supported}}}}\$  |                                                                                                                                                                                                                                                                  |
| Advanced Data Formatting (ADF) |    ${{\color{BrickRed}{\textsf{Not Supported}}}}\$     | Not planned to be added anytime soon                                                                                                                                                                                                                             |
|             TOKENS             |    ${{\color{BrickRed}{\textsf{Not Supported}}}}\$     | Not planned to be added anytime soon                                                                                                                                                                                                                             |
|             INTENT             |  ${{\color{ForestGreen}{\textsf{Fully Supported}}}}\$  |                                                                                                                                                                                                                                                                  |
|           KEYSTROKE            |  ${{\color{ForestGreen}{\textsf{Fully Supported}}}}\$  |                                                                                                                                                                                                                                                                  |
|               IP               |  ${{\color{ForestGreen}{\textsf{Fully Supported}}}}\$  |                                                                                                                                                                                                                                                                  |
|    Data Capture Plus (DCP)     |  ${{\color{ForestGreen}{\textsf{Fully Supported}}}}\$  |                                                                                                                                                                                                                                                                  |
|   Enterprise Keyboard (EKB)    |  ${{\color{ForestGreen}{\textsf{Fully Supported}}}}\$  |                                                                                                                                                                                                                                                                  |

<br>

### Other Features
- Create new profiles
- Associate applications/activities to a profile
- Disable/Enable the scanner
- Listen for incoming data/responses from DataWedge via Listeners

<br>

> [!note]  
<b>Not all the features that DataWedge provides are covered by the library so if you're looking for something in specific, feel free to reach out by writing me an email.</b>


## Usage & Examples
Working with the library is very simple, if you had previous experience with DataWedge, you will notice that the methods are following almost everytime the names associated with the original parameters.
At the same time whenever you're planning to work on a specific category of settings such as Barcode Input, you will then have to use the BarcodePlugin from the library and same goes also for other plugins such as IntentOutput, Keystroke and others...


## Authors

- James Swinton-Bland
- Daniel Neamtu

## License

[MIT](LICENSE.txt)