# Android Technical Interview
Wolox's base project for the Android's team technical interview

### Objective

Build an app that can display a list of Gifs retrieved from the public API of
[Giphy](https://giphy.com/)

The following technical challenges should be addressed during the interview:
  - REST API Integration
  - Recycler's view implementation

##### Working Example

### API Integration.

The full documentation on how to use Giphy's API is here: https://developers.giphy.com/docs/
Additonally, a summary of the API behaviour can also be found here: https://any-api.com/giphy_com/giphy_com/docs/gifs/searchGifs

#### How to use the API

For this exercise, we will use the `search` endpoint.

##### Endpoint
The endpoint looks like this:
`http://api.giphy.com/v1/gifs/search?q={SEARCH TERM}&api_key={API KEY}`

- `{SEARCH TERM}`: An URL encoded string whith a search term
- `{API KEY}`: A Giphy provided API token to authenticate the request

We already have an `API KEY` in place for this excersise that you can use for the challenge.
**API KEY:** `ek7rK0Eh2sbmBGwOFVsFBe93Stt4xQTB`

##### Response

After successfully implemeting the HTTP request, you will receive a result like this:

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

#### Suggested workflow
1) Add a `RecyclerView` to the `MainActivity`
2) Implement an HTTP request to Giphy's API and return some data
3) Integrate the Giphy's response to the `RecyclerView` and display the data.

### Good luck!

## <a name="topic-about"></a> About

This project is maintained by [Juan Ignacio Molina](https://github.com/juanignaciomolina)
and it was written by [Wolox](http://www.wolox.com.ar).

![Wolox](https://raw.githubusercontent.com/Wolox/press-kit/master/logos/logo_banner.png)

## <a name="topic-license"></a> License

**WOLMO CORE** is available under the MIT [license](https://raw.githubusercontent.com/Wolox/wolmo-core-android/master/LICENSE.md).

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
