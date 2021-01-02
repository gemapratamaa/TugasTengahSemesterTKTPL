#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_id_ac_ui_cs_mobileprogramming_gemaPratamaAditya_funPicsAndQuotes_MainActivity_introTextFromJNI(JNIEnv *env, jobject thiz) {
    std::string intro_text = "FunPicsAndQuotes by 1706040031";

    return env->NewStringUTF(intro_text.c_str());
}