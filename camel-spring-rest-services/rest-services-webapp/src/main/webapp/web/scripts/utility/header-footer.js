function createHeader() {

    const header1 = document.querySelector("#header");
    console.log(header1)

    const header = document.getElementById("header");

    const headingElementProps = {id: "heading"};
    const headingElement = createElementWithProp("div", headingElementProps);
    headingElement.innerText = "Welcome to My Login Page";

    const displayBarProps = {id: "displayBar"};
    const displayBar = createElementWithProp("div", displayBarProps);

    const displayBar1Props = {id: "displayBar1"};
    const displayBar1 = createElementWithProp("div", displayBar1Props);

    const marquee = createElementWithProp("marquee");
    marquee.innerText = 'Visit our social media pages.'

    const displayBar2Props = {id: "displayBar2"};
    const displayBar2 = createElementWithProp("div", displayBar2Props);

    const facebookIconProps = {class: "fab fa-facebook"};
    const facebookIcon = createElementWithProp("i", facebookIconProps);

    const twitterIconProps = {class: "fab fa-twitter"};
    const twitterIcon = createElementWithProp("i", twitterIconProps);

    const instagramIconProps = {class: "fab fa-instagram"};
    const instagramIcon = createElementWithProp("i", instagramIconProps);

    const facebookProps = {id: "facebook"};
    const facebook = createElementWithProp("div", facebookProps);

    const twitterProps = {id: "twitter"};
    const twitter = createElementWithProp("div", twitterProps);

    const instagramProps = {id: "instagram"};
    const instagram = createElementWithProp("div", instagramProps);

    displayBar1.appendChild(marquee);

    facebook.appendChild(facebookIcon);
    twitter.appendChild(twitterIcon);
    instagram.appendChild(instagramIcon);

    displayBar2.appendChild(facebook);
    displayBar2.appendChild(twitter);
    displayBar2.appendChild(instagram);

    displayBar.appendChild(displayBar1);
    displayBar.appendChild(displayBar2);

    header.appendChild(headingElement);
    header.appendChild(displayBar)
}

function createFooter() {
    const footer = document.querySelector("#footer");
    footer.innerText = 'This is Footer';
    footer.style.backgroundColor = 'green';
}

createHeader();
createFooter()