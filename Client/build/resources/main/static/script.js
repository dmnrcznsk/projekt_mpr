document.addEventListener("DOMContentLoaded", () => {
    const images = document.querySelectorAll(".animated-bg");
    const speed = 3;

    const generateUniqueAngle = (existingAngles) => {
        let angle;
        do {
            angle = Math.random() * 2 * Math.PI;
        } while (existingAngles.some(existing => Math.abs(Math.cos(existing) - Math.cos(angle)) < 0.1 && Math.abs(Math.sin(existing) - Math.sin(angle)) < 0.1));
        return angle;
    };

    const existingAngles = [];

    images.forEach((image) => {
        let x = window.innerWidth / 2;
        let y = window.innerHeight / 2;

        let angle = generateUniqueAngle(existingAngles);
        existingAngles.push(angle);

        let dx = Math.cos(angle) * speed;
        let dy = Math.sin(angle) * speed;

        const updatePosition = () => {
            x += dx;
            y += dy;

            if (x + image.offsetWidth >= window.innerWidth || x <= 0) {
                dx = -dx;
            }
            if (y + image.offsetHeight >= window.innerHeight || y <= 0) {
                dy = -dy;
            }

            image.style.position = "fixed";
            image.style.left = `${x}px`;
            image.style.top = `${y}px`;

            requestAnimationFrame(updatePosition);
        };

        updatePosition();
    });
});
