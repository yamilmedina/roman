/* tslint:disable */
/* eslint-disable */

/**
 * Roman Swagger
 * Roman - Wire Bots Proxy
 *
 * The version of the OpenAPI document: 1.14.0
 * Contact: dejan@wire.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

/**
 *
 * @export
 * @interface SignIn
 */
export interface SignIn {
  /**
   *
   * @type {string}
   * @memberof SignIn
   */
  email: string;
  /**
   *
   * @type {string}
   * @memberof SignIn
   */
  password: string;
}

export function SignInFromJSON(json: any): SignIn {
  return SignInFromJSONTyped(json, false);
}

export function SignInFromJSONTyped(json: any, ignoreDiscriminator: boolean): SignIn {
  if ((json === undefined) || (json === null)) {
    return json;
  }
  return {

    'email': json['email'],
    'password': json['password']
  };
}

export function SignInToJSON(value?: SignIn | null): any {
  if (value === undefined) {
    return undefined;
  }
  if (value === null) {
    return null;
  }
  return {

    'email': value.email,
    'password': value.password
  };
}


