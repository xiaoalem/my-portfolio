// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random greeting to the page.
 */
function factAboutMe() {
  const greetings =
      ['I got to know Python when I was studying Forestry  in Finland', 'Seville is my favorite city of tourism', 'I\'m fan of polar bear', 'I\'ve lived in Canada for 8 years, but I\'v never been to the capital'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('fact-container');
  greetingContainer.innerText = greeting;
}

let navlist = document.querySelector('.navlist');

navlist.classList.toggle('active');
