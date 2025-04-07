package pe.gob.osinergmin.pgim.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		Map<String, Object> info = new HashMap<String, Object>();

		UserPrincipal userInfo = (UserPrincipal)authentication.getPrincipal();	

		info.put("id", userInfo.getId());
		info.put("nombres", userInfo.getNombres());
		info.put("dependencia", userInfo.getDependencia());
		info.put("compania", userInfo.getCompania());				
		info.put("email", userInfo.getEmail());
		info.put("coUsuarioSiged", userInfo.getCodUsuarioSiged());
		info.put("passwordSiged", userInfo.getPasswordSiged());
		info.put("listaModulos", userInfo.getListaModulos());

		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

		return accessToken;
	}

}
