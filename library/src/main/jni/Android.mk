LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)


OpenCV_INSTALL_MODULES := on
OpenCV_CAMERA_MODULES := off

OPENCV_LIB_TYPE :=STATIC

ifeq ("$(wildcard $(OPENCV_MK_PATH))","")
include F:\BaseFrame\RxJavaAndRetrofit\library\native\jni\OpenCV.mk
else
include $(OPENCV_MK_PATH)
endif

LOCAL_MODULE := Scanner

LOCAL_SRC_FILES := scan.cpp

LOCAL_LDLIBS +=  -lm -llog -ljnigraphics

include $(BUILD_SHARED_LIBRARY)