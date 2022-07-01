package kyu3;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BinomialExpansion {

    private static class Term {

        private static final Pattern pattern = Pattern.compile("([+-]?\\d+)?(-?\\w)?");

        public Long a;
        public String x;
        public Integer power;

        public Term(Long a, String x, Integer power) {
            this.a = a;
            this.x = x;
            this.power = power;
        }

        public Term multiply(Term term) {

            long a = this.a == null ? term.a : this.a * term.a;
            String x = a == 0 ? null : this.x == null ? term.x : this.x;
            Integer power = this.power;

            if (this.x != null && term.x != null) {

                power++;
            }

            return new Term(a, x, power);
        }

        public Term add(Term term) {

            if (!Objects.equals(x, term.x) || !Objects.equals(power, term.power)) {

                return null;
            }

            Long a = this.a + term.a;

            return new Term(a, x, power);
        }

        @Override
        public String toString() {

            final StringBuilder sb = new StringBuilder();

            if (x == null) {
                sb.append(a);
            } else {
                if (a > 1 || a < -1) {
                    sb.append(a);
                }

                if (a == -1) {
                    sb.append("-");
                }
                sb.append(x);
            }

            if (power > 1) {
                sb.append("^").append(power);
            }

            return sb.toString();
        }

        public static Term of(String term) {

            long a = 1;
            String x = null;
            int power = 1;

            Matcher matcher = pattern.matcher(term);

            if (matcher.find()) {
                String g1 = matcher.group(1);
                x = matcher.group(2);
                String g2 = matcher.group(2);

                if (g1 != null) {
                    a = Integer.parseInt(g1);
                }

                if (g2 != null) {
                    if (g2.length() > 1) {
                        a = -1;
                        x = g2.substring(1);
                    } else {
                        x = g2;
                    }
                }
            }

            return new Term(a, x, power);
        }
    }

    private static class Polynomial {

        private static final Pattern pattern = Pattern.compile("(-?\\d*\\w)([+\\-]\\d+)");

        private final ArrayList<Term> terms = new ArrayList<>();

        public Polynomial(ArrayList<Term> terms) {

            this.terms.addAll(terms);
        }

        public Polynomial multiply(Polynomial polynomial) {

            ArrayList<Term> newTerms = new ArrayList<>();
            ArrayList<Term> newTerms1 = new ArrayList<>();

            for (Term term1 : terms) {
                for (Term term2 : polynomial.getTerms()) {
                    newTerms.add(term1.multiply(term2));
                }
            }

            for (int i = 0; i < newTerms.size(); i++) {
                if (newTerms.get(i).a == 0) {
                    continue;
                }

                if (i == newTerms.size() - 1) {
                    newTerms1.add(newTerms.get(i));
                    break;
                }
                Term t = newTerms.get(i).add(newTerms.get(i + 1));

                if (t == null) {
                    newTerms1.add(newTerms.get(i));
                } else {
                    newTerms1.add(t);
                    i++;
                }
            }

            return new Polynomial(newTerms1);
        }

        @Override
        public String toString() {

            final StringBuilder sb = new StringBuilder();

            for (int i = 0; i < terms.size() - 1; i++) {
                sb.append(terms.get(i));
                if (terms.get(i + 1).a > 0) {
                    sb.append("+");
                }
            }

            sb.append(terms.get(terms.size() - 1));

            return sb.toString();
        }

        private static Polynomial of(String polynomial) {

            Matcher matcher = pattern.matcher(polynomial);

            if (matcher.find()) {
                String a = matcher.group(1);
                String b = matcher.group(2);

                final ArrayList<Term> terms = new ArrayList<>();

                terms.add(Term.of(a));
                terms.add(Term.of(b));

                return new Polynomial(terms);
            }

            return null;
        }

        public ArrayList<Term> getTerms() {

            return terms;
        }
    }

    public static String expand(String expr) {

        Pattern pattern = Pattern.compile("\\((.*)\\)\\^(\\d+)");
        Matcher matcher = pattern.matcher(expr);

        if (matcher.find()) {
            String a = matcher.group(1);
            int power = Integer.parseInt(matcher.group(2));

            if (power == 0) {

                return "1";
            }

            Polynomial polynomial = Polynomial.of(a);

            return expandAux(polynomial, polynomial, power - 1);
        }

        return "";
    }

    public static String expandAux(Polynomial polynomial1, Polynomial polynomial2, int pow) {

        if (pow == 0) {

            return polynomial1.toString();
        } else {

            Polynomial polynomial = polynomial1.multiply(polynomial2);

            return expandAux(polynomial, polynomial2, pow - 1);
        }
    }
}
