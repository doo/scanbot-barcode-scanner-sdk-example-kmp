#!/bin/bash

VERSION=${1:-6.0.1}

BASE_URL="https://download.scanbot.io/barcode-scanner-sdk/ios/pre/xcframeworks/Beta2"
ZIP_FILE="scanbot-ios-barcode-scanner-sdk-xcframework-${VERSION}.zip"
DEST_FOLDER="scanbot_sdk"
URL="${BASE_URL}/${ZIP_FILE}"

echo "Downloading Scanbot SDK version ${VERSION}..."
curl -o "$ZIP_FILE" "$URL"
if [[ $? -ne 0 ]]; then
  echo "Failed to download the SDK. Please check the URL, version, or your network connection."
  exit 1
fi

mkdir -p "$DEST_FOLDER"
echo "Unzipping the SDK..."
unzip -o "$ZIP_FILE" -d "$DEST_FOLDER"
if [[ $? -ne 0 ]]; then
  echo "Failed to unzip the SDK. Please check the zip file."
  exit 1
fi

echo "Cleaning up..."
rm "$ZIP_FILE"
echo "Setup complete. The SDK version ${VERSION} is available in the '$DEST_FOLDER' folder."

ROOT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
XCFRAMEWORK_PATH="$ROOT_DIR/scanbot_sdk/ScanbotBarcodeScannerSDK.xcframework"
XCODEPROJ_PATH="$ROOT_DIR/iosApp/iosApp.xcodeproj"
TARGET_NAME="iosApp"

if [[ ! -d "$XCFRAMEWORK_PATH" ]]; then
  echo "Error: .xcframework not found at $XCFRAMEWORK_PATH"
  exit 1
fi

ruby <<RUBY
require 'xcodeproj'

project = Xcodeproj::Project.open('$XCODEPROJ_PATH')
target = project.targets.find { |t| t.name == '$TARGET_NAME' }
if target.nil?
  puts "Error: Target $TARGET_NAME not found in project."
  exit 1
end

framework_ref = project.new_file('$XCFRAMEWORK_PATH')
target.frameworks_build_phase.add_file_reference(framework_ref)

embed_phase = target.copy_files_build_phases.find { |phase| phase.name == 'Embed Frameworks' }
unless embed_phase
  embed_phase = target.new_copy_files_build_phase('Embed Frameworks')
  embed_phase.dst_subfolder_spec = '10' # Frameworks destination for embedding
end

build_file = embed_phase.add_file_reference(framework_ref, true)
build_file.settings = { 'ATTRIBUTES' => ['CodeSignOnCopy'] }  # Ensure "Embed & Sign"

project.save
puts "Added $XCFRAMEWORK_PATH with Embed & Sign to $TARGET_NAME in $XCODEPROJ_PATH."
RUBY
