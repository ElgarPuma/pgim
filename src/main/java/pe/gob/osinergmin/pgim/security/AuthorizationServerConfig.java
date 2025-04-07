package pe.gob.osinergmin.pgim.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import pe.gob.osinergmin.pgim.config.PropertiesConfig;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter  {
	

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;

	@Autowired
	private AuthenticationManager authenticationManager;	
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;	
	
	@Autowired
	private InfoAdicionalToken infoAdicionalToken;
	
	@Autowired
	private PropertiesConfig propertiesConfig;
	

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		.withClient(TokenUtil.SECURITY_JWT_CLIENTE_ID)
		.secret(bcrypt.encode(TokenUtil.SECURITY_JWT_CLIENTE_SECRET))
		.authorizedGrantTypes(TokenUtil.SECURITY_JWT_GRANT_TYPE,TokenUtil.SECURITY_JWT_REFRESH_TOKEN)
		//.authorizedGrantTypes(TokenUtil.SECURITY_JWT_GRANT_TYPE)
		.scopes(TokenUtil.SECURITY_JWT_SCOPE_READ, TokenUtil.SECURITY_JWT_SCOPE_WRITE)
		.resourceIds(TokenUtil.SECURITY_JWT_RESOURCE_ID)
		//.accessTokenValiditySeconds(45) // 0.5 minutos
		//.accessTokenValiditySeconds(600) // 10 minutos
		//.accessTokenValiditySeconds(1800) //30 minutos de duración del token
		.accessTokenValiditySeconds(this.propertiesConfig.getAccessTokenValiditySeconds())
		//.accessTokenValiditySeconds(2*3600) //2 horas de duración del token	
		//.refreshTokenValiditySeconds(6*3600) //Sólo será posible refrescar el token hasta un máximo de 6 horas, luego de iniciada la sessión
		.refreshTokenValiditySeconds(this.propertiesConfig.getRefreshTokenValiditySeconds());
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain enhancerChain = new TokenEnhancerChain();		
		enhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken, accessTokenConverter));
		endpoints.tokenStore(tokenStore).accessTokenConverter(accessTokenConverter).tokenEnhancer(enhancerChain)
				.authenticationManager(authenticationManager);
	}

}
