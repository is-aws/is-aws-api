// Copyright 2015 Ricardo Gladwell.
// Licensed under the GNU Affero General Public License.
// See the LICENSE file for more information.

package me.gladwell.aws.test

import unfiltered.response.Html5
import me.gladwell.aws.Views
import me.gladwell.aws.NetworkLookup
import me.gladwell.aws.InvalidInput

trait MockHtmlViews extends Views {

  override val index = Html5 {
    <head>
      <title>is-aws-api</title>
    </head>
    <body>
     <form method="GET">
       <label for="address">Address</label>
       <input type="text" name="address" id="address" />
     </form>
   </body>
  }

  override def resultView(query: String, result: NetworkLookup) = Html5 {
    <head>
      <title>is-aws-api</title>
    </head>
    <body>
      <div id="result">
        <p><span id="is-aws">{result.hosted}</span></p>
        {validationMessage(result.validation)}
      </div>
    </body>
  }

  override def errorView(error: Exception) = Html5 {
    <head>
      <title>is-aws-api</title>
    </head>
    <body>
      <div id="error">
        <p>{error.getMessage}</p>
      </div>
    </body>
  }

  private def validationMessage(error: Option[InvalidInput]) = {
    error match {
      case Some(invalid)  => <p class={invalid.id}>{invalid.message}</p>
      case None           => <p></p>
    }
  }

}
