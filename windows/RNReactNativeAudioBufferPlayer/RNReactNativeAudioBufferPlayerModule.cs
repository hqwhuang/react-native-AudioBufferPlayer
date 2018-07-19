using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace React.Native.Audio.Buffer.Player.RNReactNativeAudioBufferPlayer
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNReactNativeAudioBufferPlayerModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNReactNativeAudioBufferPlayerModule"/>.
        /// </summary>
        internal RNReactNativeAudioBufferPlayerModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNReactNativeAudioBufferPlayer";
            }
        }
    }
}
