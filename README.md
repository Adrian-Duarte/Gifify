# Android Technical Challange
Wolox's base project for the Android's team technical interview for experienced developers.

### Objective

This technical challenge consists of building a mobile app for the Android platform (obviously) using native code.

The following concepts will be addressed during this technical challenge:
  - REST API Integration
  - UI lists implementation
  - Android's app architecture
  - Local data
  - Kotlin language usage

## About _Gifify_

Your challenge is to be the sole developer of _Gifify_, the brand new Gif discovery app for [Giphy](https://giphy.com/), a website that holds a collection of Gifs.

With Gifify the end user will be able to discover new Gifs, save them for later use and share them with their contacts, so their friends can see how cool that new cat Gif is.

The app will make us of the public API of [Giphy](https://giphy.com/), we'll discuss that later on a section below.

### Instructions

First of all, clone this project. It already includes a pre-configured Kotlin project with some model classes that may help you.

At Wolox we love independent and problem-solving people, so in general terms you can use whatever you see fit to solve this challenge. We won't hold you back with dependencies restrictions or things like that. If you find a library that you like, you can use it for this project.

Having said that, since this is a technical challenge after all, we expect to see a couple of key points on your code.

* **UI/UX**: You can implement the UI however you prefer. We know that you are a programmer, not a UI/UX designer, so we won't be super demanding on this. However, if you have a good eye for design and can delight us with some eye candy, we'll love to see that.
* **Architecture**: Business logic on fragments is a bad idea. Please use any architecture you are comfortable with (like MVP or MVVM) to make this app.
* **Animations**: Give your app some life and animate something. Doesn't matter much what you animate, but make sure to use at least one animation on your project.
* **Kotlin**: The project must be coded on Kotlin. If you only now Java, basic Kotlin can be learned in one day and it's a great addition for your Android developer career.
* **Internationalization (i18n)**: The in-app texts can be in either English or Spanish. You don't need to translate the app, but make sure to use that nice `strings.xml` file to store the texts to support translations.
* **Testing**: Test your business logic. Please test at least one of your Presenters, Controllers or ViewModels (or wherever your business logic resides in) with some unit tests.
* **Performance**: Make sure your app feels fluid. We won't be too picky with this as long as the UI feels fast.
* **Code readability**: Always have in mind that your code will be reviewed by developers at Wolox, so code something that we can easily understand. Comments and other forms of documentation are not strictly required, but consider using them if you are unsure whether we will understand why something is implemented the way it is or not.

### Challenge delivery

You'll receive a deadline for this challenge. If something unexpected happens and you think you won't be able to finish the project before the end date, please reach out to us as soon as possible to review your case.

The challenge will be delivered to us by a Github repo. Just make a new repo, work on it and send us the link when you are done so we can check it out (make sure the repo is public or that you give use the proper permissions so we can access it).
If, for whatever reason, you don't want to work on a Github repo, send us a `.zip` with the code once you have finished.

### Screens

##### Home screen
This screen will be the entry point to the app. The user should open the app and see a list of random gifs, using Giphy API.
This view should also have a search bar, where the user can search a term and see a list of the gifs that match that criteria. Please reuse the same list used for the random gifs initially displayed.

If the user clicks on a Gif, a modal/dialog should be displayed allowing the user to share the Gif with his contacts or social networks. Implement this however you like.

Whenever the user long-clicks on a Gif, it should be saved as a favorite.

Gifs that are favorited by the user should be saved on the local storage of the device. Please use the SQLite database to store any necessary data of the favorited Gif so you can display it later. Remember that you can use any library or framework that you prefer, there is no need to write plain SQL when there are libs out there that can help you out.


##### Favorites screen
Similar to the Home screen, but only displays a list of Gifs that were favorited by the user.

Just like in the Home screen, if the user clicks on a Gif, the sharing modal/dialog should be displayed.

If the user long-clicks on a given Gif, a modal/dialog should be displayed asking the users if they wants to remove the Gif from their favorites. If the user decides to remove the Gifs from his favorites it should be removed.

## API Integration.

The full documentation on how to use Giphy's API is here: https://developers.giphy.com/docs/
Additonally, a summary of the API behaviour can also be found here: https://any-api.com/giphy_com/giphy_com/docs/gifs/searchGifs

#### How to use the API

As an example, the `search` endpoint looks like this:
`http://api.giphy.com/v1/gifs/search?q={SEARCH TERM}&api_key={API KEY}`

- `{SEARCH TERM}`: An URL encoded string whith a search term
- `{API KEY}`: A Giphy provided API token to authenticate the request

We already have an `API KEY` in place that you can use for the challenge.
**API KEY:** `ek7rK0Eh2sbmBGwOFVsFBe93Stt4xQTB`

##### Response

After having successfully implemented the HTTP request, you will receive a response like this:

```
{
    "data": [
        {
            type: "gif",
            id: "FiGiRei2ICzzG",
            slug: "funny-cat-FiGiRei2ICzzG",
            url: "http://giphy.com/gifs/funny-cat-FiGiRei2ICzzG",
            bitly_gif_url: "http://gph.is/1fIdLOl",
            bitly_url: "http://gph.is/1fIdLOl",
            embed_url: "http://giphy.com/embed/FiGiRei2ICzzG",
            username: "",
            source: "http://tumblr.com",
            rating: "g",
            caption: "",
            content_url: "",
            source_tld: "tumblr.com",
            source_post_url: "http://tumblr.com",
            import_datetime: "2014-01-18 09:14:20",
            trending_datetime: "1970-01-01 00:00:00",
            images: {
                fixed_height: {
                    url: "http://media2.giphy.com/media/FiGiRei2ICzzG/200.gif",
                    width: "568",
                    height: "200",
                    size: "460622",
                    mp4: "http://media2.giphy.com/media/FiGiRei2ICzzG/200.mp4",
                    mp4_size: "13866",
                    webp: "http://media2.giphy.com/media/FiGiRei2ICzzG/200.webp",
                    webp_size: "367786"
                },
                fixed_height_still: {
                    url: "http://media2.giphy.com/media/FiGiRei2ICzzG/200_s.gif",
                    width: "568",
                    height: "200"
                },
                fixed_height_downsampled: {
                    url: "http://media2.giphy.com/media/FiGiRei2ICzzG/200_d.gif",
                    width: "568",
                    height: "200",
                    size: "476276",
                    webp: "http://media2.giphy.com/media/FiGiRei2ICzzG/200_d.webp",
                    webp_size: "100890"
                },
                fixed_width: {
                    url: "http://media2.giphy.com/media/FiGiRei2ICzzG/200w.gif",
                    width: "200",
                    height: "70",
                    size: "90483",
                    mp4: "http://media2.giphy.com/media/FiGiRei2ICzzG/200w.mp4",
                    mp4_size: "14238",
                    webp: "http://media2.giphy.com/media/FiGiRei2ICzzG/200w.webp",
                    webp_size: "47302"
                },
                fixed_width_still: {
                    url: "http://media2.giphy.com/media/FiGiRei2ICzzG/200w_s.gif",
                    width: "200",
                    height: "70"
                },
                fixed_width_downsampled: {
                    url: "http://media2.giphy.com/media/FiGiRei2ICzzG/200w_d.gif",
                    width: "200",
                    height: "70",
                    size: "71069",
                    webp: "http://media2.giphy.com/media/FiGiRei2ICzzG/200w_d.webp",
                    webp_size: "13186"
                },
                fixed_height_small: {
                    url: "http://media2.giphy.com/media/FiGiRei2ICzzG/100.gif",
                    width: "284",
                    height: "100",
                    size: "460622",
                    webp: "http://media2.giphy.com/media/FiGiRei2ICzzG/100.webp",
                    webp_size: "72748"
                },
                fixed_height_small_still: {
                    url: "http://media2.giphy.com/media/FiGiRei2ICzzG/100_s.gif",
                    width: "284",
                    height: "100"
                },
                fixed_width_small: {
                    url: "http://media2.giphy.com/media/FiGiRei2ICzzG/100w.gif",
                    width: "100",
                    height: "35",
                    size: "90483",
                    webp: "http://media2.giphy.com/media/FiGiRei2ICzzG/100w.webp",
                    webp_size: "18298"
                },
                fixed_width_small_still: {
                    url: "http://media2.giphy.com/media/FiGiRei2ICzzG/100w_s.gif",
                    width: "100",
                    height: "35"
                },
                downsized: {
                    url: "http://media2.giphy.com/media/FiGiRei2ICzzG/giphy.gif",
                    width: "500",
                    height: "176",
                    size: "426811"
                },
                downsized_still: {
                    url: "http://media2.giphy.com/media/FiGiRei2ICzzG/giphy_s.gif",
                    width: "500",
                    height: "176"
                },
                downsized_large: {
                    url: "http://media2.giphy.com/media/FiGiRei2ICzzG/giphy.gif",
                    width: "500",
                    height: "176",
                    size: "426811"
                },
                original: {
                    url: "http://media2.giphy.com/media/FiGiRei2ICzzG/giphy.gif",
                    width: "500",
                    height: "176",
                    size: "426811",
                    frames: "22",
                    mp4: "http://media2.giphy.com/media/FiGiRei2ICzzG/giphy.mp4",
                    mp4_size: "51432",
                    webp: "http://media2.giphy.com/media/FiGiRei2ICzzG/giphy.webp",
                    webp_size: "291616"
                },
                original_still: {
                    url: "http://media2.giphy.com/media/FiGiRei2ICzzG/giphy_s.gif",
                    width: "500",
                    height: "176"
                }
            },
            title: "Funny Cat GIF",
        },
        ... 24 more items
    ],
    "meta": {
        "status": 200,
        "msg": "OK"
    },
    "pagination": {
        "total_count": 1947,
        "count": 25,
        "offset": 0
    }
}
```

#### Models

For your convenience, we already included Giphy's response objects inside the `/models` package. This should help you kickstart the code for the HTTP request without dealing with potential typos and things like that.

Inside the `/models` package you will find the following models:
  - `GiphyResponse`: The top level wrapper for Giphy's response
  - `Gif`: An object representing all the Gif's data
  - `Images`: An object containing different versions of the `ImageDetails` object
  - `ImageDetails`: This object contains the `URL` for the path to the actual GIF image on Giphy's servers.

### Questions
If you have doubts about this challenge or need further clarification of anything, please reach out to us!
Asking any question that you have about this project won't hurt your chances of completing the challenge, if anything they can actually improved them.

If you don't have the contact details of the developers that will be reviewing your challenge, ask the HR woloxer that is handling your recruitment process for them.

### Good luck!

This project is maintained by [Juan Ignacio Molina](https://github.com/juanignaciomolina)
and it was written by [Wolox](http://www.wolox.com.ar).

![Wolox](https://raw.githubusercontent.com/Wolox/press-kit/master/logos/logo_banner.png)

## License

**This code challange** is available under the MIT License.

    Copyright (c) Wolox S.A

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
