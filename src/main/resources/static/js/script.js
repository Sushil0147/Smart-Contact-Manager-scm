
document.addEventListener("DOMContentLoaded", function () {
    const button = document.getElementById("change_theme");
    const html = document.documentElement;
    const spanText = button.querySelector("span");

    // 1. Load theme from localStorage if available
    const savedTheme = localStorage.getItem("theme");
    if (savedTheme === "dark" || savedTheme === "light") {
        html.className = savedTheme;
        updateButtonText(savedTheme);
    }

    // 2. Button click toggles theme
    button.addEventListener("click", () => {
        const currentTheme = html.classList.contains("dark") ? "dark" : "light";
        const newTheme = currentTheme === "light" ? "dark" : "light";

        // Update <html> class
        html.classList.remove(currentTheme);
        html.classList.add(newTheme);

        // Update button text and store new theme
        updateButtonText(newTheme);
        localStorage.setItem("theme", newTheme); // 👈 Save to localStorage
    });

    // 3. Helper to update button text
    function updateButtonText(theme) {
        spanText.textContent = theme === "dark" ? "Light" : "Dark";
    }
});

