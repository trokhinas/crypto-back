package vsu.labs.crypto.utils.data;

public final class MessageUtils {

    private MessageUtils() { }

    public static MessageBuilder buildMessage(String template) {
        return new MessageBuilder(template);
    }

    public static class MessageBuilder {
        private final String template;

        private MessageBuilder(String template) {
            this.template = template;
        }

        public String withArgs(Object...args) {
            return String.format(template, args);
        }
    }
}
