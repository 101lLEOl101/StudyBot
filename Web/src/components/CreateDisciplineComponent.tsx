import {
    ActionIcon, Box,
    Button,
    Divider,
    Group,
    Paper,
    PaperProps,
    Stack,
    Text,
    TextInput,
} from '@mantine/core';
import { useForm } from '@mantine/form';
import {Link} from "react-router-dom";
import { IoCloseCircleOutline } from "react-icons/io5";

export function CreateDisciplineComponent(props: PaperProps) {
    const form = useForm({
        initialValues: {
            name: '',
            second_name: '',
            password: '',
            repeat_password: '',
        },

        validate: {
            password: (val) => (val.length <= 6 ? 'Пароль должен иметь не менее 6 символов' : null),
            repeat_password: (val, vals) => (val !== vals.password ? 'Повторный пароль неверен' : null)
        },
    });

    return (
        <Paper radius="md" p="xl" pt={"5"} withBorder {...props}>
            <Box display={"flex"} ml={"100%"}>
                <Link to={"/disciplines"}>
                    <ActionIcon  radius={100} variant="subtle" color="red">
                        <IoCloseCircleOutline size={32}/>
                    </ActionIcon>
                </Link>
            </Box>
            <Text size="lg" ta={"center"} fw={500}>
                Дисциплина
            </Text>

            <Divider label={'Создание'} labelPosition="center" my="lg" />

            <form onSubmit={form.onSubmit(() => {})}>
                <Stack>
                    <TextInput
                        label="Название"
                        placeholder="Название Дисциплины"
                        value={form.values.name}
                        onChange={(event) => form.setFieldValue('name', event.currentTarget.value)}
                        radius="md"
                    />
                </Stack>

                <Group justify="end" mt="xl">
                    <Button type="submit" radius="xl" >
                        Создать Дисциплину
                    </Button>
                </Group>
            </form>
        </Paper>
    );
}