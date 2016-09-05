/*
 * Copyright 2013 Opencard Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.RNOpenpay.openpay.services;

import com.RNOpenpay.openpay.exceptions.OpenpayServiceException;
import com.RNOpenpay.openpay.exceptions.ServiceUnavailableException;
import com.RNOpenpay.openpay.model.Card;
import com.RNOpenpay.openpay.model.Token;

/**
 * @author Luis Delucio
 *
 */
public class TokenService extends BaseService<Card, Token> {
	private static final String MERCHANT_TOKENS = "tokens";

	public TokenService(final String baseUrl, final String merchantId, final String apiKey) {
		super(baseUrl, merchantId, apiKey, Token.class);
	}

	public Token create(final Card card) throws OpenpayServiceException, ServiceUnavailableException {
		return this.post(MERCHANT_TOKENS, card);
	}

}
