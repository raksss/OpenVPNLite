package net.openvpn.openvpn;

public class ovpncliJNI {
    public static final native boolean ClientAPI_Config_altProxy_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_altProxy_set(long j, ClientAPI_Config clientAPI_Config, boolean z);

    public static final native boolean ClientAPI_Config_autologinSessions_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_autologinSessions_set(long j, ClientAPI_Config clientAPI_Config, boolean z);

    public static final native long ClientAPI_Config_clockTickMS_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_clockTickMS_set(long j, ClientAPI_Config clientAPI_Config, long j2);

    public static final native String ClientAPI_Config_compressionMode_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_compressionMode_set(long j, ClientAPI_Config clientAPI_Config, String str);

    public static final native int ClientAPI_Config_connTimeout_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_connTimeout_set(long j, ClientAPI_Config clientAPI_Config, int i);

    public static final native long ClientAPI_Config_contentList_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_contentList_set(long j, ClientAPI_Config clientAPI_Config, long j2);

    public static final native String ClientAPI_Config_content_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_content_set(long j, ClientAPI_Config clientAPI_Config, String str);

    public static final native boolean ClientAPI_Config_dco_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_dco_set(long j, ClientAPI_Config clientAPI_Config, boolean z);

    public static final native int ClientAPI_Config_defaultKeyDirection_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_defaultKeyDirection_set(long j, ClientAPI_Config clientAPI_Config, int i);

    public static final native boolean ClientAPI_Config_disableClientCert_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_disableClientCert_set(long j, ClientAPI_Config clientAPI_Config, boolean z);

    public static final native boolean ClientAPI_Config_echo_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_echo_set(long j, ClientAPI_Config clientAPI_Config, boolean z);

    public static final native String ClientAPI_Config_externalPkiAlias_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_externalPkiAlias_set(long j, ClientAPI_Config clientAPI_Config, String str);

    public static final native boolean ClientAPI_Config_forceAesCbcCiphersuites_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_forceAesCbcCiphersuites_set(long j, ClientAPI_Config clientAPI_Config, boolean z);

    public static final native boolean ClientAPI_Config_googleDnsFallback_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_googleDnsFallback_set(long j, ClientAPI_Config clientAPI_Config, boolean z);

    public static final native String ClientAPI_Config_gremlinConfig_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_gremlinConfig_set(long j, ClientAPI_Config clientAPI_Config, String str);

    public static final native String ClientAPI_Config_guiVersion_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_guiVersion_set(long j, ClientAPI_Config clientAPI_Config, String str);

    public static final native boolean ClientAPI_Config_info_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_info_set(long j, ClientAPI_Config clientAPI_Config, boolean z);

    public static final native String ClientAPI_Config_ipv6_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_ipv6_set(long j, ClientAPI_Config clientAPI_Config, String str);

    public static final native long ClientAPI_Config_peerInfo_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_peerInfo_set(long j, ClientAPI_Config clientAPI_Config, long j2);

    public static final native String ClientAPI_Config_portOverride_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_portOverride_set(long j, ClientAPI_Config clientAPI_Config, String str);

    public static final native String ClientAPI_Config_privateKeyPassword_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_privateKeyPassword_set(long j, ClientAPI_Config clientAPI_Config, String str);

    public static final native String ClientAPI_Config_protoOverride_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_protoOverride_set(long j, ClientAPI_Config clientAPI_Config, String str);

    public static final native boolean ClientAPI_Config_proxyAllowCleartextAuth_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_proxyAllowCleartextAuth_set(long j, ClientAPI_Config clientAPI_Config, boolean z);

    public static final native String ClientAPI_Config_proxyHost_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_proxyHost_set(long j, ClientAPI_Config clientAPI_Config, String str);

    public static final native String ClientAPI_Config_proxyPassword_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_proxyPassword_set(long j, ClientAPI_Config clientAPI_Config, String str);

    public static final native String ClientAPI_Config_proxyPort_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_proxyPort_set(long j, ClientAPI_Config clientAPI_Config, String str);

    public static final native String ClientAPI_Config_proxyUsername_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_proxyUsername_set(long j, ClientAPI_Config clientAPI_Config, String str);

    public static final native String ClientAPI_Config_serverOverride_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_serverOverride_set(long j, ClientAPI_Config clientAPI_Config, String str);

    public static final native int ClientAPI_Config_sslDebugLevel_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_sslDebugLevel_set(long j, ClientAPI_Config clientAPI_Config, int i);

    public static final native boolean ClientAPI_Config_synchronousDnsLookup_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_synchronousDnsLookup_set(long j, ClientAPI_Config clientAPI_Config, boolean z);

    public static final native String ClientAPI_Config_tlsCertProfileOverride_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_tlsCertProfileOverride_set(long j, ClientAPI_Config clientAPI_Config, String str);

    public static final native String ClientAPI_Config_tlsVersionMinOverride_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_tlsVersionMinOverride_set(long j, ClientAPI_Config clientAPI_Config, String str);

    public static final native boolean ClientAPI_Config_tunPersist_get(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_Config_tunPersist_set(long j, ClientAPI_Config clientAPI_Config, boolean z);

    public static final native String ClientAPI_ConnectionInfo_clientIp_get(long j, ClientAPI_ConnectionInfo clientAPI_ConnectionInfo);

    public static final native void ClientAPI_ConnectionInfo_clientIp_set(long j, ClientAPI_ConnectionInfo clientAPI_ConnectionInfo, String str);

    public static final native boolean ClientAPI_ConnectionInfo_defined_get(long j, ClientAPI_ConnectionInfo clientAPI_ConnectionInfo);

    public static final native void ClientAPI_ConnectionInfo_defined_set(long j, ClientAPI_ConnectionInfo clientAPI_ConnectionInfo, boolean z);

    public static final native String ClientAPI_ConnectionInfo_gw4_get(long j, ClientAPI_ConnectionInfo clientAPI_ConnectionInfo);

    public static final native void ClientAPI_ConnectionInfo_gw4_set(long j, ClientAPI_ConnectionInfo clientAPI_ConnectionInfo, String str);

    public static final native String ClientAPI_ConnectionInfo_gw6_get(long j, ClientAPI_ConnectionInfo clientAPI_ConnectionInfo);

    public static final native void ClientAPI_ConnectionInfo_gw6_set(long j, ClientAPI_ConnectionInfo clientAPI_ConnectionInfo, String str);

    public static final native String ClientAPI_ConnectionInfo_serverHost_get(long j, ClientAPI_ConnectionInfo clientAPI_ConnectionInfo);

    public static final native void ClientAPI_ConnectionInfo_serverHost_set(long j, ClientAPI_ConnectionInfo clientAPI_ConnectionInfo, String str);

    public static final native String ClientAPI_ConnectionInfo_serverIp_get(long j, ClientAPI_ConnectionInfo clientAPI_ConnectionInfo);

    public static final native void ClientAPI_ConnectionInfo_serverIp_set(long j, ClientAPI_ConnectionInfo clientAPI_ConnectionInfo, String str);

    public static final native String ClientAPI_ConnectionInfo_serverPort_get(long j, ClientAPI_ConnectionInfo clientAPI_ConnectionInfo);

    public static final native void ClientAPI_ConnectionInfo_serverPort_set(long j, ClientAPI_ConnectionInfo clientAPI_ConnectionInfo, String str);

    public static final native String ClientAPI_ConnectionInfo_serverProto_get(long j, ClientAPI_ConnectionInfo clientAPI_ConnectionInfo);

    public static final native void ClientAPI_ConnectionInfo_serverProto_set(long j, ClientAPI_ConnectionInfo clientAPI_ConnectionInfo, String str);

    public static final native String ClientAPI_ConnectionInfo_tunName_get(long j, ClientAPI_ConnectionInfo clientAPI_ConnectionInfo);

    public static final native void ClientAPI_ConnectionInfo_tunName_set(long j, ClientAPI_ConnectionInfo clientAPI_ConnectionInfo, String str);

    public static final native String ClientAPI_ConnectionInfo_user_get(long j, ClientAPI_ConnectionInfo clientAPI_ConnectionInfo);

    public static final native void ClientAPI_ConnectionInfo_user_set(long j, ClientAPI_ConnectionInfo clientAPI_ConnectionInfo, String str);

    public static final native String ClientAPI_ConnectionInfo_vpnIp4_get(long j, ClientAPI_ConnectionInfo clientAPI_ConnectionInfo);

    public static final native void ClientAPI_ConnectionInfo_vpnIp4_set(long j, ClientAPI_ConnectionInfo clientAPI_ConnectionInfo, String str);

    public static final native String ClientAPI_ConnectionInfo_vpnIp6_get(long j, ClientAPI_ConnectionInfo clientAPI_ConnectionInfo);

    public static final native void ClientAPI_ConnectionInfo_vpnIp6_set(long j, ClientAPI_ConnectionInfo clientAPI_ConnectionInfo, String str);

    public static final native String ClientAPI_DynamicChallenge_challenge_get(long j, ClientAPI_DynamicChallenge clientAPI_DynamicChallenge);

    public static final native void ClientAPI_DynamicChallenge_challenge_set(long j, ClientAPI_DynamicChallenge clientAPI_DynamicChallenge, String str);

    public static final native boolean ClientAPI_DynamicChallenge_echo_get(long j, ClientAPI_DynamicChallenge clientAPI_DynamicChallenge);

    public static final native void ClientAPI_DynamicChallenge_echo_set(long j, ClientAPI_DynamicChallenge clientAPI_DynamicChallenge, boolean z);

    public static final native boolean ClientAPI_DynamicChallenge_responseRequired_get(long j, ClientAPI_DynamicChallenge clientAPI_DynamicChallenge);

    public static final native void ClientAPI_DynamicChallenge_responseRequired_set(long j, ClientAPI_DynamicChallenge clientAPI_DynamicChallenge, boolean z);

    public static final native String ClientAPI_DynamicChallenge_stateID_get(long j, ClientAPI_DynamicChallenge clientAPI_DynamicChallenge);

    public static final native void ClientAPI_DynamicChallenge_stateID_set(long j, ClientAPI_DynamicChallenge clientAPI_DynamicChallenge, String str);

    public static final native boolean ClientAPI_EvalConfig_allowPasswordSave_get(long j, ClientAPI_EvalConfig clientAPI_EvalConfig);

    public static final native void ClientAPI_EvalConfig_allowPasswordSave_set(long j, ClientAPI_EvalConfig clientAPI_EvalConfig, boolean z);

    public static final native boolean ClientAPI_EvalConfig_autologin_get(long j, ClientAPI_EvalConfig clientAPI_EvalConfig);

    public static final native void ClientAPI_EvalConfig_autologin_set(long j, ClientAPI_EvalConfig clientAPI_EvalConfig, boolean z);

    public static final native boolean ClientAPI_EvalConfig_error_get(long j, ClientAPI_EvalConfig clientAPI_EvalConfig);

    public static final native void ClientAPI_EvalConfig_error_set(long j, ClientAPI_EvalConfig clientAPI_EvalConfig, boolean z);

    public static final native boolean ClientAPI_EvalConfig_externalPki_get(long j, ClientAPI_EvalConfig clientAPI_EvalConfig);

    public static final native void ClientAPI_EvalConfig_externalPki_set(long j, ClientAPI_EvalConfig clientAPI_EvalConfig, boolean z);

    public static final native String ClientAPI_EvalConfig_friendlyName_get(long j, ClientAPI_EvalConfig clientAPI_EvalConfig);

    public static final native void ClientAPI_EvalConfig_friendlyName_set(long j, ClientAPI_EvalConfig clientAPI_EvalConfig, String str);

    public static final native String ClientAPI_EvalConfig_message_get(long j, ClientAPI_EvalConfig clientAPI_EvalConfig);

    public static final native void ClientAPI_EvalConfig_message_set(long j, ClientAPI_EvalConfig clientAPI_EvalConfig, String str);

    public static final native boolean ClientAPI_EvalConfig_privateKeyPasswordRequired_get(long j, ClientAPI_EvalConfig clientAPI_EvalConfig);

    public static final native void ClientAPI_EvalConfig_privateKeyPasswordRequired_set(long j, ClientAPI_EvalConfig clientAPI_EvalConfig, boolean z);

    public static final native String ClientAPI_EvalConfig_profileName_get(long j, ClientAPI_EvalConfig clientAPI_EvalConfig);

    public static final native void ClientAPI_EvalConfig_profileName_set(long j, ClientAPI_EvalConfig clientAPI_EvalConfig, String str);

    public static final native String ClientAPI_EvalConfig_remoteHost_get(long j, ClientAPI_EvalConfig clientAPI_EvalConfig);

    public static final native void ClientAPI_EvalConfig_remoteHost_set(long j, ClientAPI_EvalConfig clientAPI_EvalConfig, String str);

    public static final native String ClientAPI_EvalConfig_remotePort_get(long j, ClientAPI_EvalConfig clientAPI_EvalConfig);

    public static final native void ClientAPI_EvalConfig_remotePort_set(long j, ClientAPI_EvalConfig clientAPI_EvalConfig, String str);

    public static final native String ClientAPI_EvalConfig_remoteProto_get(long j, ClientAPI_EvalConfig clientAPI_EvalConfig);

    public static final native void ClientAPI_EvalConfig_remoteProto_set(long j, ClientAPI_EvalConfig clientAPI_EvalConfig, String str);

    public static final native long ClientAPI_EvalConfig_serverList_get(long j, ClientAPI_EvalConfig clientAPI_EvalConfig);

    public static final native void ClientAPI_EvalConfig_serverList_set(long j, ClientAPI_EvalConfig clientAPI_EvalConfig, long j2, ClientAPI_ServerEntryVector clientAPI_ServerEntryVector);

    public static final native boolean ClientAPI_EvalConfig_staticChallengeEcho_get(long j, ClientAPI_EvalConfig clientAPI_EvalConfig);

    public static final native void ClientAPI_EvalConfig_staticChallengeEcho_set(long j, ClientAPI_EvalConfig clientAPI_EvalConfig, boolean z);

    public static final native String ClientAPI_EvalConfig_staticChallenge_get(long j, ClientAPI_EvalConfig clientAPI_EvalConfig);

    public static final native void ClientAPI_EvalConfig_staticChallenge_set(long j, ClientAPI_EvalConfig clientAPI_EvalConfig, String str);

    public static final native String ClientAPI_EvalConfig_userlockedUsername_get(long j, ClientAPI_EvalConfig clientAPI_EvalConfig);

    public static final native void ClientAPI_EvalConfig_userlockedUsername_set(long j, ClientAPI_EvalConfig clientAPI_EvalConfig, String str);

    public static final native boolean ClientAPI_Event_error_get(long j, ClientAPI_Event clientAPI_Event);

    public static final native void ClientAPI_Event_error_set(long j, ClientAPI_Event clientAPI_Event, boolean z);

    public static final native boolean ClientAPI_Event_fatal_get(long j, ClientAPI_Event clientAPI_Event);

    public static final native void ClientAPI_Event_fatal_set(long j, ClientAPI_Event clientAPI_Event, boolean z);

    public static final native String ClientAPI_Event_info_get(long j, ClientAPI_Event clientAPI_Event);

    public static final native void ClientAPI_Event_info_set(long j, ClientAPI_Event clientAPI_Event, String str);

    public static final native String ClientAPI_Event_name_get(long j, ClientAPI_Event clientAPI_Event);

    public static final native void ClientAPI_Event_name_set(long j, ClientAPI_Event clientAPI_Event, String str);

    public static final native boolean ClientAPI_ExternalPKIBase_sign(long j, ClientAPI_ExternalPKIBase clientAPI_ExternalPKIBase, String str, long j2);

    public static final native long ClientAPI_ExternalPKICertRequest_SWIGUpcast(long j);

    public static final native String ClientAPI_ExternalPKICertRequest_cert_get(long j, ClientAPI_ExternalPKICertRequest clientAPI_ExternalPKICertRequest);

    public static final native void ClientAPI_ExternalPKICertRequest_cert_set(long j, ClientAPI_ExternalPKICertRequest clientAPI_ExternalPKICertRequest, String str);

    public static final native String ClientAPI_ExternalPKICertRequest_supportingChain_get(long j, ClientAPI_ExternalPKICertRequest clientAPI_ExternalPKICertRequest);

    public static final native void ClientAPI_ExternalPKICertRequest_supportingChain_set(long j, ClientAPI_ExternalPKICertRequest clientAPI_ExternalPKICertRequest, String str);

    public static final native String ClientAPI_ExternalPKIRequestBase_alias_get(long j, ClientAPI_ExternalPKIRequestBase clientAPI_ExternalPKIRequestBase);

    public static final native void ClientAPI_ExternalPKIRequestBase_alias_set(long j, ClientAPI_ExternalPKIRequestBase clientAPI_ExternalPKIRequestBase, String str);

    public static final native String ClientAPI_ExternalPKIRequestBase_errorText_get(long j, ClientAPI_ExternalPKIRequestBase clientAPI_ExternalPKIRequestBase);

    public static final native void ClientAPI_ExternalPKIRequestBase_errorText_set(long j, ClientAPI_ExternalPKIRequestBase clientAPI_ExternalPKIRequestBase, String str);

    public static final native boolean ClientAPI_ExternalPKIRequestBase_error_get(long j, ClientAPI_ExternalPKIRequestBase clientAPI_ExternalPKIRequestBase);

    public static final native void ClientAPI_ExternalPKIRequestBase_error_set(long j, ClientAPI_ExternalPKIRequestBase clientAPI_ExternalPKIRequestBase, boolean z);

    public static final native boolean ClientAPI_ExternalPKIRequestBase_invalidAlias_get(long j, ClientAPI_ExternalPKIRequestBase clientAPI_ExternalPKIRequestBase);

    public static final native void ClientAPI_ExternalPKIRequestBase_invalidAlias_set(long j, ClientAPI_ExternalPKIRequestBase clientAPI_ExternalPKIRequestBase, boolean z);

    public static final native long ClientAPI_ExternalPKISignRequest_SWIGUpcast(long j);

    public static final native String ClientAPI_ExternalPKISignRequest_data_get(long j, ClientAPI_ExternalPKISignRequest clientAPI_ExternalPKISignRequest);

    public static final native void ClientAPI_ExternalPKISignRequest_data_set(long j, ClientAPI_ExternalPKISignRequest clientAPI_ExternalPKISignRequest, String str);

    public static final native String ClientAPI_ExternalPKISignRequest_sig_get(long j, ClientAPI_ExternalPKISignRequest clientAPI_ExternalPKISignRequest);

    public static final native void ClientAPI_ExternalPKISignRequest_sig_set(long j, ClientAPI_ExternalPKISignRequest clientAPI_ExternalPKISignRequest, String str);

    public static final native long ClientAPI_InterfaceStats_bytesIn_get(long j, ClientAPI_InterfaceStats clientAPI_InterfaceStats);

    public static final native void ClientAPI_InterfaceStats_bytesIn_set(long j, ClientAPI_InterfaceStats clientAPI_InterfaceStats, long j2);

    public static final native long ClientAPI_InterfaceStats_bytesOut_get(long j, ClientAPI_InterfaceStats clientAPI_InterfaceStats);

    public static final native void ClientAPI_InterfaceStats_bytesOut_set(long j, ClientAPI_InterfaceStats clientAPI_InterfaceStats, long j2);

    public static final native long ClientAPI_InterfaceStats_errorsIn_get(long j, ClientAPI_InterfaceStats clientAPI_InterfaceStats);

    public static final native void ClientAPI_InterfaceStats_errorsIn_set(long j, ClientAPI_InterfaceStats clientAPI_InterfaceStats, long j2);

    public static final native long ClientAPI_InterfaceStats_errorsOut_get(long j, ClientAPI_InterfaceStats clientAPI_InterfaceStats);

    public static final native void ClientAPI_InterfaceStats_errorsOut_set(long j, ClientAPI_InterfaceStats clientAPI_InterfaceStats, long j2);

    public static final native long ClientAPI_InterfaceStats_packetsIn_get(long j, ClientAPI_InterfaceStats clientAPI_InterfaceStats);

    public static final native void ClientAPI_InterfaceStats_packetsIn_set(long j, ClientAPI_InterfaceStats clientAPI_InterfaceStats, long j2);

    public static final native long ClientAPI_InterfaceStats_packetsOut_get(long j, ClientAPI_InterfaceStats clientAPI_InterfaceStats);

    public static final native void ClientAPI_InterfaceStats_packetsOut_set(long j, ClientAPI_InterfaceStats clientAPI_InterfaceStats, long j2);

    public static final native String ClientAPI_KeyValue_key_get(long j, ClientAPI_KeyValue clientAPI_KeyValue);

    public static final native void ClientAPI_KeyValue_key_set(long j, ClientAPI_KeyValue clientAPI_KeyValue, String str);

    public static final native String ClientAPI_KeyValue_value_get(long j, ClientAPI_KeyValue clientAPI_KeyValue);

    public static final native void ClientAPI_KeyValue_value_set(long j, ClientAPI_KeyValue clientAPI_KeyValue, String str);

    public static final native void ClientAPI_LLVector_add(long j, ClientAPI_LLVector clientAPI_LLVector, long j2);

    public static final native long ClientAPI_LLVector_capacity(long j, ClientAPI_LLVector clientAPI_LLVector);

    public static final native void ClientAPI_LLVector_clear(long j, ClientAPI_LLVector clientAPI_LLVector);

    public static final native long ClientAPI_LLVector_get(long j, ClientAPI_LLVector clientAPI_LLVector, int i);

    public static final native boolean ClientAPI_LLVector_isEmpty(long j, ClientAPI_LLVector clientAPI_LLVector);

    public static final native void ClientAPI_LLVector_reserve(long j, ClientAPI_LLVector clientAPI_LLVector, long j2);

    public static final native void ClientAPI_LLVector_set(long j, ClientAPI_LLVector clientAPI_LLVector, int i, long j2);

    public static final native long ClientAPI_LLVector_size(long j, ClientAPI_LLVector clientAPI_LLVector);

    public static final native String ClientAPI_LogInfo_text_get(long j, ClientAPI_LogInfo clientAPI_LogInfo);

    public static final native void ClientAPI_LogInfo_text_set(long j, ClientAPI_LogInfo clientAPI_LogInfo, String str);

    public static final native String ClientAPI_MergeConfig_basename_get(long j, ClientAPI_MergeConfig clientAPI_MergeConfig);

    public static final native void ClientAPI_MergeConfig_basename_set(long j, ClientAPI_MergeConfig clientAPI_MergeConfig, String str);

    public static final native String ClientAPI_MergeConfig_errorText_get(long j, ClientAPI_MergeConfig clientAPI_MergeConfig);

    public static final native void ClientAPI_MergeConfig_errorText_set(long j, ClientAPI_MergeConfig clientAPI_MergeConfig, String str);

    public static final native String ClientAPI_MergeConfig_profileContent_get(long j, ClientAPI_MergeConfig clientAPI_MergeConfig);

    public static final native void ClientAPI_MergeConfig_profileContent_set(long j, ClientAPI_MergeConfig clientAPI_MergeConfig, String str);

    public static final native long ClientAPI_MergeConfig_refPathList_get(long j, ClientAPI_MergeConfig clientAPI_MergeConfig);

    public static final native void ClientAPI_MergeConfig_refPathList_set(long j, ClientAPI_MergeConfig clientAPI_MergeConfig, long j2);

    public static final native String ClientAPI_MergeConfig_status_get(long j, ClientAPI_MergeConfig clientAPI_MergeConfig);

    public static final native void ClientAPI_MergeConfig_status_set(long j, ClientAPI_MergeConfig clientAPI_MergeConfig, String str);

    public static final native long ClientAPI_OpenVPNClient_SWIGUpcast(long j);

    public static final native int ClientAPI_OpenVPNClient_app_expire();

    public static final native void ClientAPI_OpenVPNClient_change_ownership(ClientAPI_OpenVPNClient clientAPI_OpenVPNClient, long j, boolean z);

    public static final native void ClientAPI_OpenVPNClient_clock_tick(long j, ClientAPI_OpenVPNClient clientAPI_OpenVPNClient);

    public static final native void ClientAPI_OpenVPNClient_clock_tickSwigExplicitClientAPI_OpenVPNClient(long j, ClientAPI_OpenVPNClient clientAPI_OpenVPNClient);

    public static final native long ClientAPI_OpenVPNClient_connect(long j, ClientAPI_OpenVPNClient clientAPI_OpenVPNClient);

    public static final native long ClientAPI_OpenVPNClient_connection_info(long j, ClientAPI_OpenVPNClient clientAPI_OpenVPNClient);

    public static final native String ClientAPI_OpenVPNClient_copyright();

    public static final native String ClientAPI_OpenVPNClient_crypto_self_test();

    public static final native void ClientAPI_OpenVPNClient_director_connect(ClientAPI_OpenVPNClient clientAPI_OpenVPNClient, long j, boolean z, boolean z2);

    public static final native long ClientAPI_OpenVPNClient_eval_config(long j, ClientAPI_OpenVPNClient clientAPI_OpenVPNClient, long j2, ClientAPI_Config clientAPI_Config);

    public static final native long ClientAPI_OpenVPNClient_eval_config_static(long j, ClientAPI_Config clientAPI_Config);

    public static final native void ClientAPI_OpenVPNClient_event(long j, ClientAPI_OpenVPNClient clientAPI_OpenVPNClient, long j2, ClientAPI_Event clientAPI_Event);

    public static final native void ClientAPI_OpenVPNClient_external_pki_cert_request(long j, ClientAPI_OpenVPNClient clientAPI_OpenVPNClient, long j2, ClientAPI_ExternalPKICertRequest clientAPI_ExternalPKICertRequest);

    public static final native void ClientAPI_OpenVPNClient_external_pki_sign_request(long j, ClientAPI_OpenVPNClient clientAPI_OpenVPNClient, long j2, ClientAPI_ExternalPKISignRequest clientAPI_ExternalPKISignRequest);

    public static final native void ClientAPI_OpenVPNClient_init_process();

    public static final native void ClientAPI_OpenVPNClient_log(long j, ClientAPI_OpenVPNClient clientAPI_OpenVPNClient, long j2, ClientAPI_LogInfo clientAPI_LogInfo);

    public static final native int ClientAPI_OpenVPNClient_max_profile_size();

    public static final native long ClientAPI_OpenVPNClient_merge_config_static(String str, boolean z);

    public static final native long ClientAPI_OpenVPNClient_merge_config_string_static(String str);

    public static final native boolean ClientAPI_OpenVPNClient_parse_dynamic_challenge(String str, long j, ClientAPI_DynamicChallenge clientAPI_DynamicChallenge);

    public static final native void ClientAPI_OpenVPNClient_pause(long j, ClientAPI_OpenVPNClient clientAPI_OpenVPNClient, String str);

    public static final native boolean ClientAPI_OpenVPNClient_pause_on_connection_timeout(long j, ClientAPI_OpenVPNClient clientAPI_OpenVPNClient);

    public static final native String ClientAPI_OpenVPNClient_platform();

    public static final native void ClientAPI_OpenVPNClient_post_cc_msg(long j, ClientAPI_OpenVPNClient clientAPI_OpenVPNClient, String str);

    public static final native long ClientAPI_OpenVPNClient_provide_creds(long j, ClientAPI_OpenVPNClient clientAPI_OpenVPNClient, long j2, ClientAPI_ProvideCreds clientAPI_ProvideCreds);

    public static final native void ClientAPI_OpenVPNClient_reconnect(long j, ClientAPI_OpenVPNClient clientAPI_OpenVPNClient, int i);

    public static final native void ClientAPI_OpenVPNClient_remote_override(long j, ClientAPI_OpenVPNClient clientAPI_OpenVPNClient, long j2, ClientAPI_RemoteOverride clientAPI_RemoteOverride);

    public static final native void ClientAPI_OpenVPNClient_remote_overrideSwigExplicitClientAPI_OpenVPNClient(long j, ClientAPI_OpenVPNClient clientAPI_OpenVPNClient, long j2, ClientAPI_RemoteOverride clientAPI_RemoteOverride);

    public static final native boolean ClientAPI_OpenVPNClient_remote_override_enabled(long j, ClientAPI_OpenVPNClient clientAPI_OpenVPNClient);

    public static final native boolean ClientAPI_OpenVPNClient_remote_override_enabledSwigExplicitClientAPI_OpenVPNClient(long j, ClientAPI_OpenVPNClient clientAPI_OpenVPNClient);

    public static final native void ClientAPI_OpenVPNClient_resume(long j, ClientAPI_OpenVPNClient clientAPI_OpenVPNClient);

    public static final native boolean ClientAPI_OpenVPNClient_session_token(long j, ClientAPI_OpenVPNClient clientAPI_OpenVPNClient, long j2, ClientAPI_SessionToken clientAPI_SessionToken);

    public static final native boolean ClientAPI_OpenVPNClient_socket_protect(long j, ClientAPI_OpenVPNClient clientAPI_OpenVPNClient, int i);

    public static final native long ClientAPI_OpenVPNClient_stats_bundle(long j, ClientAPI_OpenVPNClient clientAPI_OpenVPNClient);

    public static final native int ClientAPI_OpenVPNClient_stats_n();

    public static final native String ClientAPI_OpenVPNClient_stats_name(int i);

    public static final native long ClientAPI_OpenVPNClient_stats_value(long j, ClientAPI_OpenVPNClient clientAPI_OpenVPNClient, int i);

    public static final native void ClientAPI_OpenVPNClient_stop(long j, ClientAPI_OpenVPNClient clientAPI_OpenVPNClient);

    public static final native long ClientAPI_OpenVPNClient_transport_stats(long j, ClientAPI_OpenVPNClient clientAPI_OpenVPNClient);

    public static final native long ClientAPI_OpenVPNClient_tun_stats(long j, ClientAPI_OpenVPNClient clientAPI_OpenVPNClient);

    public static final native void ClientAPI_OpenVPNClient_uninit_process();

    public static final native boolean ClientAPI_ProvideCreds_cachePassword_get(long j, ClientAPI_ProvideCreds clientAPI_ProvideCreds);

    public static final native void ClientAPI_ProvideCreds_cachePassword_set(long j, ClientAPI_ProvideCreds clientAPI_ProvideCreds, boolean z);

    public static final native String ClientAPI_ProvideCreds_dynamicChallengeCookie_get(long j, ClientAPI_ProvideCreds clientAPI_ProvideCreds);

    public static final native void ClientAPI_ProvideCreds_dynamicChallengeCookie_set(long j, ClientAPI_ProvideCreds clientAPI_ProvideCreds, String str);

    public static final native String ClientAPI_ProvideCreds_password_get(long j, ClientAPI_ProvideCreds clientAPI_ProvideCreds);

    public static final native void ClientAPI_ProvideCreds_password_set(long j, ClientAPI_ProvideCreds clientAPI_ProvideCreds, String str);

    public static final native boolean ClientAPI_ProvideCreds_replacePasswordWithSessionID_get(long j, ClientAPI_ProvideCreds clientAPI_ProvideCreds);

    public static final native void ClientAPI_ProvideCreds_replacePasswordWithSessionID_set(long j, ClientAPI_ProvideCreds clientAPI_ProvideCreds, boolean z);

    public static final native String ClientAPI_ProvideCreds_response_get(long j, ClientAPI_ProvideCreds clientAPI_ProvideCreds);

    public static final native void ClientAPI_ProvideCreds_response_set(long j, ClientAPI_ProvideCreds clientAPI_ProvideCreds, String str);

    public static final native String ClientAPI_ProvideCreds_username_get(long j, ClientAPI_ProvideCreds clientAPI_ProvideCreds);

    public static final native void ClientAPI_ProvideCreds_username_set(long j, ClientAPI_ProvideCreds clientAPI_ProvideCreds, String str);

    public static final native String ClientAPI_RemoteOverride_host_get(long j, ClientAPI_RemoteOverride clientAPI_RemoteOverride);

    public static final native void ClientAPI_RemoteOverride_host_set(long j, ClientAPI_RemoteOverride clientAPI_RemoteOverride, String str);

    public static final native String ClientAPI_RemoteOverride_ip_get(long j, ClientAPI_RemoteOverride clientAPI_RemoteOverride);

    public static final native void ClientAPI_RemoteOverride_ip_set(long j, ClientAPI_RemoteOverride clientAPI_RemoteOverride, String str);

    public static final native String ClientAPI_RemoteOverride_port_get(long j, ClientAPI_RemoteOverride clientAPI_RemoteOverride);

    public static final native void ClientAPI_RemoteOverride_port_set(long j, ClientAPI_RemoteOverride clientAPI_RemoteOverride, String str);

    public static final native String ClientAPI_RemoteOverride_proto_get(long j, ClientAPI_RemoteOverride clientAPI_RemoteOverride);

    public static final native void ClientAPI_RemoteOverride_proto_set(long j, ClientAPI_RemoteOverride clientAPI_RemoteOverride, String str);

    public static final native void ClientAPI_ServerEntryVector_add(long j, ClientAPI_ServerEntryVector clientAPI_ServerEntryVector, long j2, ClientAPI_ServerEntry clientAPI_ServerEntry);

    public static final native long ClientAPI_ServerEntryVector_capacity(long j, ClientAPI_ServerEntryVector clientAPI_ServerEntryVector);

    public static final native void ClientAPI_ServerEntryVector_clear(long j, ClientAPI_ServerEntryVector clientAPI_ServerEntryVector);

    public static final native long ClientAPI_ServerEntryVector_get(long j, ClientAPI_ServerEntryVector clientAPI_ServerEntryVector, int i);

    public static final native boolean ClientAPI_ServerEntryVector_isEmpty(long j, ClientAPI_ServerEntryVector clientAPI_ServerEntryVector);

    public static final native void ClientAPI_ServerEntryVector_reserve(long j, ClientAPI_ServerEntryVector clientAPI_ServerEntryVector, long j2);

    public static final native void ClientAPI_ServerEntryVector_set(long j, ClientAPI_ServerEntryVector clientAPI_ServerEntryVector, int i, long j2, ClientAPI_ServerEntry clientAPI_ServerEntry);

    public static final native long ClientAPI_ServerEntryVector_size(long j, ClientAPI_ServerEntryVector clientAPI_ServerEntryVector);

    public static final native String ClientAPI_ServerEntry_friendlyName_get(long j, ClientAPI_ServerEntry clientAPI_ServerEntry);

    public static final native void ClientAPI_ServerEntry_friendlyName_set(long j, ClientAPI_ServerEntry clientAPI_ServerEntry, String str);

    public static final native String ClientAPI_ServerEntry_server_get(long j, ClientAPI_ServerEntry clientAPI_ServerEntry);

    public static final native void ClientAPI_ServerEntry_server_set(long j, ClientAPI_ServerEntry clientAPI_ServerEntry, String str);

    public static final native String ClientAPI_SessionToken_session_id_get(long j, ClientAPI_SessionToken clientAPI_SessionToken);

    public static final native void ClientAPI_SessionToken_session_id_set(long j, ClientAPI_SessionToken clientAPI_SessionToken, String str);

    public static final native String ClientAPI_SessionToken_username_get(long j, ClientAPI_SessionToken clientAPI_SessionToken);

    public static final native void ClientAPI_SessionToken_username_set(long j, ClientAPI_SessionToken clientAPI_SessionToken, String str);

    public static final native boolean ClientAPI_Status_error_get(long j, ClientAPI_Status clientAPI_Status);

    public static final native void ClientAPI_Status_error_set(long j, ClientAPI_Status clientAPI_Status, boolean z);

    public static final native String ClientAPI_Status_message_get(long j, ClientAPI_Status clientAPI_Status);

    public static final native void ClientAPI_Status_message_set(long j, ClientAPI_Status clientAPI_Status, String str);

    public static final native String ClientAPI_Status_status_get(long j, ClientAPI_Status clientAPI_Status);

    public static final native void ClientAPI_Status_status_set(long j, ClientAPI_Status clientAPI_Status, String str);

    public static final native long ClientAPI_TransportStats_bytesIn_get(long j, ClientAPI_TransportStats clientAPI_TransportStats);

    public static final native void ClientAPI_TransportStats_bytesIn_set(long j, ClientAPI_TransportStats clientAPI_TransportStats, long j2);

    public static final native long ClientAPI_TransportStats_bytesOut_get(long j, ClientAPI_TransportStats clientAPI_TransportStats);

    public static final native void ClientAPI_TransportStats_bytesOut_set(long j, ClientAPI_TransportStats clientAPI_TransportStats, long j2);

    public static final native int ClientAPI_TransportStats_lastPacketReceived_get(long j, ClientAPI_TransportStats clientAPI_TransportStats);

    public static final native void ClientAPI_TransportStats_lastPacketReceived_set(long j, ClientAPI_TransportStats clientAPI_TransportStats, int i);

    public static final native long ClientAPI_TransportStats_packetsIn_get(long j, ClientAPI_TransportStats clientAPI_TransportStats);

    public static final native void ClientAPI_TransportStats_packetsIn_set(long j, ClientAPI_TransportStats clientAPI_TransportStats, long j2);

    public static final native long ClientAPI_TransportStats_packetsOut_get(long j, ClientAPI_TransportStats clientAPI_TransportStats);

    public static final native void ClientAPI_TransportStats_packetsOut_set(long j, ClientAPI_TransportStats clientAPI_TransportStats, long j2);

    public static final native boolean ClientAPI_TunBuilderBase_tun_builder_add_address(long j, ClientAPI_TunBuilderBase clientAPI_TunBuilderBase, String str, int i, String str2, boolean z, boolean z2);

    public static final native boolean ClientAPI_TunBuilderBase_tun_builder_add_dns_server(long j, ClientAPI_TunBuilderBase clientAPI_TunBuilderBase, String str, boolean z);

    public static final native boolean ClientAPI_TunBuilderBase_tun_builder_add_proxy_bypass(long j, ClientAPI_TunBuilderBase clientAPI_TunBuilderBase, String str);

    public static final native boolean ClientAPI_TunBuilderBase_tun_builder_add_route(long j, ClientAPI_TunBuilderBase clientAPI_TunBuilderBase, String str, int i, int i2, boolean z);

    public static final native boolean ClientAPI_TunBuilderBase_tun_builder_add_search_domain(long j, ClientAPI_TunBuilderBase clientAPI_TunBuilderBase, String str);

    public static final native boolean ClientAPI_TunBuilderBase_tun_builder_add_wins_server(long j, ClientAPI_TunBuilderBase clientAPI_TunBuilderBase, String str);

    public static final native int ClientAPI_TunBuilderBase_tun_builder_establish(long j, ClientAPI_TunBuilderBase clientAPI_TunBuilderBase);

    public static final native void ClientAPI_TunBuilderBase_tun_builder_establish_lite(long j, ClientAPI_TunBuilderBase clientAPI_TunBuilderBase);

    public static final native boolean ClientAPI_TunBuilderBase_tun_builder_exclude_route(long j, ClientAPI_TunBuilderBase clientAPI_TunBuilderBase, String str, int i, int i2, boolean z);

    public static final native boolean ClientAPI_TunBuilderBase_tun_builder_new(long j, ClientAPI_TunBuilderBase clientAPI_TunBuilderBase);

    public static final native boolean ClientAPI_TunBuilderBase_tun_builder_persist(long j, ClientAPI_TunBuilderBase clientAPI_TunBuilderBase);

    public static final native boolean ClientAPI_TunBuilderBase_tun_builder_reroute_gw(long j, ClientAPI_TunBuilderBase clientAPI_TunBuilderBase, boolean z, boolean z2, long j2);

    public static final native boolean ClientAPI_TunBuilderBase_tun_builder_set_adapter_domain_suffix(long j, ClientAPI_TunBuilderBase clientAPI_TunBuilderBase, String str);

    public static final native boolean ClientAPI_TunBuilderBase_tun_builder_set_block_ipv6(long j, ClientAPI_TunBuilderBase clientAPI_TunBuilderBase, boolean z);

    public static final native boolean ClientAPI_TunBuilderBase_tun_builder_set_layer(long j, ClientAPI_TunBuilderBase clientAPI_TunBuilderBase, int i);

    public static final native boolean ClientAPI_TunBuilderBase_tun_builder_set_mtu(long j, ClientAPI_TunBuilderBase clientAPI_TunBuilderBase, int i);

    public static final native boolean ClientAPI_TunBuilderBase_tun_builder_set_proxy_auto_config_url(long j, ClientAPI_TunBuilderBase clientAPI_TunBuilderBase, String str);

    public static final native boolean ClientAPI_TunBuilderBase_tun_builder_set_proxy_http(long j, ClientAPI_TunBuilderBase clientAPI_TunBuilderBase, String str, int i);

    public static final native boolean ClientAPI_TunBuilderBase_tun_builder_set_proxy_https(long j, ClientAPI_TunBuilderBase clientAPI_TunBuilderBase, String str, int i);

    public static final native boolean ClientAPI_TunBuilderBase_tun_builder_set_remote_address(long j, ClientAPI_TunBuilderBase clientAPI_TunBuilderBase, String str, boolean z);

    public static final native boolean ClientAPI_TunBuilderBase_tun_builder_set_route_metric_default(long j, ClientAPI_TunBuilderBase clientAPI_TunBuilderBase, int i);

    public static final native boolean ClientAPI_TunBuilderBase_tun_builder_set_session_name(long j, ClientAPI_TunBuilderBase clientAPI_TunBuilderBase, String str);

    public static final native void ClientAPI_TunBuilderBase_tun_builder_teardown(long j, ClientAPI_TunBuilderBase clientAPI_TunBuilderBase, boolean z);

    public static final native void delete_ClientAPI_Config(long j);

    public static final native void delete_ClientAPI_ConnectionInfo(long j);

    public static final native void delete_ClientAPI_DynamicChallenge(long j);

    public static final native void delete_ClientAPI_EvalConfig(long j);

    public static final native void delete_ClientAPI_Event(long j);

    public static final native void delete_ClientAPI_ExternalPKIBase(long j);

    public static final native void delete_ClientAPI_ExternalPKICertRequest(long j);

    public static final native void delete_ClientAPI_ExternalPKIRequestBase(long j);

    public static final native void delete_ClientAPI_ExternalPKISignRequest(long j);

    public static final native void delete_ClientAPI_InterfaceStats(long j);

    public static final native void delete_ClientAPI_KeyValue(long j);

    public static final native void delete_ClientAPI_LLVector(long j);

    public static final native void delete_ClientAPI_LogInfo(long j);

    public static final native void delete_ClientAPI_MergeConfig(long j);

    public static final native void delete_ClientAPI_OpenVPNClient(long j);

    public static final native void delete_ClientAPI_ProvideCreds(long j);

    public static final native void delete_ClientAPI_RemoteOverride(long j);

    public static final native void delete_ClientAPI_ServerEntry(long j);

    public static final native void delete_ClientAPI_ServerEntryVector(long j);

    public static final native void delete_ClientAPI_SessionToken(long j);

    public static final native void delete_ClientAPI_Status(long j);

    public static final native void delete_ClientAPI_TransportStats(long j);

    public static final native void delete_ClientAPI_TunBuilderBase(long j);

    public static final native long new_ClientAPI_Config();

    public static final native long new_ClientAPI_ConnectionInfo();

    public static final native long new_ClientAPI_DynamicChallenge();

    public static final native long new_ClientAPI_EvalConfig();

    public static final native long new_ClientAPI_Event();

    public static final native long new_ClientAPI_ExternalPKICertRequest();

    public static final native long new_ClientAPI_ExternalPKIRequestBase();

    public static final native long new_ClientAPI_ExternalPKISignRequest();

    public static final native long new_ClientAPI_InterfaceStats();

    public static final native long new_ClientAPI_KeyValue__SWIG_0();

    public static final native long new_ClientAPI_KeyValue__SWIG_1(String str, String str2);

    public static final native long new_ClientAPI_LLVector__SWIG_0();

    public static final native long new_ClientAPI_LLVector__SWIG_1(long j);

    public static final native long new_ClientAPI_LogInfo__SWIG_0();

    public static final native long new_ClientAPI_LogInfo__SWIG_1(String str);

    public static final native long new_ClientAPI_MergeConfig();

    public static final native long new_ClientAPI_OpenVPNClient();

    public static final native long new_ClientAPI_ProvideCreds();

    public static final native long new_ClientAPI_RemoteOverride();

    public static final native long new_ClientAPI_ServerEntry();

    public static final native long new_ClientAPI_ServerEntryVector__SWIG_0();

    public static final native long new_ClientAPI_ServerEntryVector__SWIG_1(long j);

    public static final native long new_ClientAPI_SessionToken();

    public static final native long new_ClientAPI_Status();

    public static final native long new_ClientAPI_TransportStats();

    public static final native long new_ClientAPI_TunBuilderBase();

    private static final native void swig_module_init();

    public static boolean SwigDirector_ClientAPI_OpenVPNClient_tun_builder_new(ClientAPI_OpenVPNClient jself) {
        return jself.tun_builder_new();
    }

    public static boolean SwigDirector_ClientAPI_OpenVPNClient_tun_builder_set_layer(ClientAPI_OpenVPNClient jself, int layer) {
        return jself.tun_builder_set_layer(layer);
    }

    public static boolean SwigDirector_ClientAPI_OpenVPNClient_tun_builder_set_remote_address(ClientAPI_OpenVPNClient jself, String address, boolean ipv6) {
        return jself.tun_builder_set_remote_address(address, ipv6);
    }

    public static boolean SwigDirector_ClientAPI_OpenVPNClient_tun_builder_add_address(ClientAPI_OpenVPNClient jself, String address, int prefix_length, String gateway, boolean ipv6, boolean net30) {
        return jself.tun_builder_add_address(address, prefix_length, gateway, ipv6, net30);
    }

    public static boolean SwigDirector_ClientAPI_OpenVPNClient_tun_builder_set_route_metric_default(ClientAPI_OpenVPNClient jself, int metric) {
        return jself.tun_builder_set_route_metric_default(metric);
    }

    public static boolean SwigDirector_ClientAPI_OpenVPNClient_tun_builder_reroute_gw(ClientAPI_OpenVPNClient jself, boolean ipv4, boolean ipv6, long flags) {
        return jself.tun_builder_reroute_gw(ipv4, ipv6, flags);
    }

    public static boolean SwigDirector_ClientAPI_OpenVPNClient_tun_builder_add_route(ClientAPI_OpenVPNClient jself, String address, int prefix_length, int metric, boolean ipv6) {
        return jself.tun_builder_add_route(address, prefix_length, metric, ipv6);
    }

    public static boolean SwigDirector_ClientAPI_OpenVPNClient_tun_builder_exclude_route(ClientAPI_OpenVPNClient jself, String address, int prefix_length, int metric, boolean ipv6) {
        return jself.tun_builder_exclude_route(address, prefix_length, metric, ipv6);
    }

    public static boolean SwigDirector_ClientAPI_OpenVPNClient_tun_builder_add_dns_server(ClientAPI_OpenVPNClient jself, String address, boolean ipv6) {
        return jself.tun_builder_add_dns_server(address, ipv6);
    }

    public static boolean SwigDirector_ClientAPI_OpenVPNClient_tun_builder_add_search_domain(ClientAPI_OpenVPNClient jself, String domain) {
        return jself.tun_builder_add_search_domain(domain);
    }

    public static boolean SwigDirector_ClientAPI_OpenVPNClient_tun_builder_set_mtu(ClientAPI_OpenVPNClient jself, int mtu) {
        return jself.tun_builder_set_mtu(mtu);
    }

    public static boolean SwigDirector_ClientAPI_OpenVPNClient_tun_builder_set_session_name(ClientAPI_OpenVPNClient jself, String name) {
        return jself.tun_builder_set_session_name(name);
    }

    public static boolean SwigDirector_ClientAPI_OpenVPNClient_tun_builder_add_proxy_bypass(ClientAPI_OpenVPNClient jself, String bypass_host) {
        return jself.tun_builder_add_proxy_bypass(bypass_host);
    }

    public static boolean SwigDirector_ClientAPI_OpenVPNClient_tun_builder_set_proxy_auto_config_url(ClientAPI_OpenVPNClient jself, String url) {
        return jself.tun_builder_set_proxy_auto_config_url(url);
    }

    public static boolean SwigDirector_ClientAPI_OpenVPNClient_tun_builder_set_proxy_http(ClientAPI_OpenVPNClient jself, String host, int port) {
        return jself.tun_builder_set_proxy_http(host, port);
    }

    public static boolean SwigDirector_ClientAPI_OpenVPNClient_tun_builder_set_proxy_https(ClientAPI_OpenVPNClient jself, String host, int port) {
        return jself.tun_builder_set_proxy_https(host, port);
    }

    public static boolean SwigDirector_ClientAPI_OpenVPNClient_tun_builder_add_wins_server(ClientAPI_OpenVPNClient jself, String address) {
        return jself.tun_builder_add_wins_server(address);
    }

    public static boolean SwigDirector_ClientAPI_OpenVPNClient_tun_builder_set_block_ipv6(ClientAPI_OpenVPNClient jself, boolean block_ipv6) {
        return jself.tun_builder_set_block_ipv6(block_ipv6);
    }

    public static boolean SwigDirector_ClientAPI_OpenVPNClient_tun_builder_set_adapter_domain_suffix(ClientAPI_OpenVPNClient jself, String name) {
        return jself.tun_builder_set_adapter_domain_suffix(name);
    }

    public static int SwigDirector_ClientAPI_OpenVPNClient_tun_builder_establish(ClientAPI_OpenVPNClient jself) {
        return jself.tun_builder_establish();
    }

    public static boolean SwigDirector_ClientAPI_OpenVPNClient_tun_builder_persist(ClientAPI_OpenVPNClient jself) {
        return jself.tun_builder_persist();
    }

    public static void SwigDirector_ClientAPI_OpenVPNClient_tun_builder_establish_lite(ClientAPI_OpenVPNClient jself) {
        jself.tun_builder_establish_lite();
    }

    public static void SwigDirector_ClientAPI_OpenVPNClient_tun_builder_teardown(ClientAPI_OpenVPNClient jself, boolean disconnect) {
        jself.tun_builder_teardown(disconnect);
    }

    public static boolean SwigDirector_ClientAPI_OpenVPNClient_socket_protect(ClientAPI_OpenVPNClient jself, int socket) {
        return jself.socket_protect(socket);
    }

    public static boolean SwigDirector_ClientAPI_OpenVPNClient_pause_on_connection_timeout(ClientAPI_OpenVPNClient jself) {
        return jself.pause_on_connection_timeout();
    }

    public static void SwigDirector_ClientAPI_OpenVPNClient_event(ClientAPI_OpenVPNClient jself, long arg0) {
        jself.event(new ClientAPI_Event(arg0, false));
    }

    public static void SwigDirector_ClientAPI_OpenVPNClient_log(ClientAPI_OpenVPNClient jself, long arg0) {
        jself.log(new ClientAPI_LogInfo(arg0, false));
    }

    public static void SwigDirector_ClientAPI_OpenVPNClient_external_pki_cert_request(ClientAPI_OpenVPNClient jself, long arg0) {
        jself.external_pki_cert_request(new ClientAPI_ExternalPKICertRequest(arg0, false));
    }

    public static void SwigDirector_ClientAPI_OpenVPNClient_external_pki_sign_request(ClientAPI_OpenVPNClient jself, long arg0) {
        jself.external_pki_sign_request(new ClientAPI_ExternalPKISignRequest(arg0, false));
    }

    public static boolean SwigDirector_ClientAPI_OpenVPNClient_remote_override_enabled(ClientAPI_OpenVPNClient jself) {
        return jself.remote_override_enabled();
    }

    public static void SwigDirector_ClientAPI_OpenVPNClient_remote_override(ClientAPI_OpenVPNClient jself, long arg0) {
        jself.remote_override(new ClientAPI_RemoteOverride(arg0, false));
    }

    public static void SwigDirector_ClientAPI_OpenVPNClient_clock_tick(ClientAPI_OpenVPNClient jself) {
        jself.clock_tick();
    }

    static {
        swig_module_init();
    }
}
