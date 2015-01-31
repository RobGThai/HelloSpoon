#!/bin/bash

function abspath() {
    # generate absolute path from relative path
    # $1     : relative filename
    # return : absolute path
  if [ -d "$1" ]; then
    # dir
    echo "$(cd "$1"; pwd)"
  elif [ -f "$1" ]; then
    # file
    if [[ $1 == */* ]]; then
      echo "$(cd "${1%/*}"; pwd)/${1##*/}"
    else
      echo "$(pwd)/$1"
    fi
  fi
}

function shallWeBail() {
  if [ "$?" == 1 ]; then
    echo "I'M OUTTA HERE!!!"
    exit 1
  fi
}

function openReportInBrowser() {
if [ "$1" == "yes" ]; then
  echo "Opening report $abs_path/spoon-output/index.html"
  open $abs_path/spoon-output/index.html
else
  echo "Report available at $abs_path/spoon-output/index.html"
fi
}

#Start
echo 'Loading properties file'
current_dir=$(dirname "$BASH_SOURCE")
abs_path=$(abspath $current_dir)
user_dir=$(pwd)
#echo "Current dir: $current_dir"
#echo "From dir: $user_dir"
cd $current_dir
. setting.properties
if [ "$android_sdk" ]; then
  echo "SDK: $android_sdk"
  /bin/bash copy_apk.sh
  shallWeBail
  /bin/bash run.sh debug.apk test.apk $android_sdk
  shallWeBail
  openReportInBrowser $open_browser
else
  echo 'Please set android_sdk in setting.properties'
fi
